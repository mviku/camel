/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.camel.reifier;

import java.time.Duration;
import java.util.Map;
import java.util.Set;

import org.apache.camel.CamelContext;
import org.apache.camel.Endpoint;
import org.apache.camel.Expression;
import org.apache.camel.NoSuchBeanException;
import org.apache.camel.NoSuchEndpointException;
import org.apache.camel.Predicate;
import org.apache.camel.Route;
import org.apache.camel.RuntimeCamelException;
import org.apache.camel.model.ExpressionSubElementDefinition;
import org.apache.camel.model.language.ExpressionDefinition;
import org.apache.camel.reifier.language.ExpressionReifier;
import org.apache.camel.spi.BeanRepository;
import org.apache.camel.support.CamelContextHelper;
import org.apache.camel.support.PropertyBindingSupport;
import org.apache.camel.util.ObjectHelper;
import org.apache.camel.util.StringHelper;

public abstract class AbstractReifier implements BeanRepository {

    protected final org.apache.camel.Route route;
    protected final CamelContext camelContext;

    public AbstractReifier(Route route) {
        this.route = ObjectHelper.notNull(route, "Route");
        this.camelContext = route.getCamelContext();
    }

    public AbstractReifier(CamelContext camelContext) {
        this.route = null;
        this.camelContext = ObjectHelper.notNull(camelContext, "CamelContext");
    }

    protected String parseString(String text) {
        return CamelContextHelper.parseText(camelContext, text);
    }

    protected Boolean parseBoolean(String text) {
        return CamelContextHelper.parseBoolean(camelContext, text);
    }

    protected boolean parseBoolean(String text, boolean def) {
        Boolean b = parseBoolean(text);
        return b != null ? b : def;
    }

    protected Long parseLong(String text) {
        return CamelContextHelper.parseLong(camelContext, text);
    }

    protected long parseLong(String text, long def) {
        Long l = parseLong(text);
        return l != null ? l : def;
    }

    protected Long parseDuration(String text) {
        Duration d = CamelContextHelper.parseDuration(camelContext, text);
        return d != null ? d.toMillis() : null;
    }

    protected long parseDuration(String text, long def) {
        Duration d = CamelContextHelper.parseDuration(camelContext, text);
        return d != null ? d.toMillis() : def;
    }

    protected Integer parseInt(String text) {
        return CamelContextHelper.parseInteger(camelContext, text);
    }

    protected int parseInt(String text, int def) {
        Integer i = parseInt(text);
        return i != null ? i : def;
    }

    protected Float parseFloat(String text) {
        return CamelContextHelper.parseFloat(camelContext, text);
    }

    protected float parseFloat(String text, float def) {
        Float f = parseFloat(text);
        return f != null ? f : def;
    }

    protected <T> T parse(Class<T> clazz, String text) {
        return CamelContextHelper.parse(camelContext, clazz, text);
    }

    protected <T> T parse(Class<T> clazz, Object text) {
        if (text instanceof String) {
            text = parseString((String) text);
        }
        return CamelContextHelper.convertTo(camelContext, clazz, text);
    }

    protected Expression createExpression(ExpressionDefinition expression) {
        return ExpressionReifier.reifier(camelContext, expression).createExpression();
    }

    protected Expression createExpression(ExpressionSubElementDefinition expression) {
        return ExpressionReifier.reifier(camelContext, expression).createExpression();
    }

    protected Predicate createPredicate(ExpressionDefinition expression) {
        return ExpressionReifier.reifier(camelContext, expression).createPredicate();
    }

    protected Predicate createPredicate(ExpressionSubElementDefinition expression) {
        return ExpressionReifier.reifier(camelContext, expression).createPredicate();
    }

    protected Object or(Object a, Object b) {
        return a != null ? a : b;
    }

    protected Object asRef(String s) {
        return s != null ? s.startsWith("#") ? s : "#" + s : null;
    }

    protected BeanRepository getRegistry() {
        return camelContext.getRegistry();
    }

    public <T> T mandatoryLookup(String name, Class<T> beanType) {
        // lookup in registry first
        Object obj = CamelContextHelper.lookup(camelContext, name, beanType);
        if (obj == null) {
            // fallback in case the name uses #class #type or other syntax
            obj = lookupByName(name);
        }
        if (obj != null) {
            obj = camelContext.getTypeConverter().convertTo(beanType, obj);
        }
        if (obj == null) {
            throw new NoSuchBeanException(name, beanType.getName());
        }
        return beanType.cast(obj);
    }

    public <T> T findSingleByType(Class<T> type) {
        return CamelContextHelper.findByType(camelContext, type);
    }

    @Override
    public Object lookupByName(String name) {
        if (name != null && name.startsWith("#class:")) {
            return createBean(name, Object.class);
        } else if (name != null && name.startsWith("#type:")) {
            return lookupBean(name, Object.class);
        } else {
            return getRegistry().lookupByName(name);
        }
    }

    public <T> T lookup(String name, Class<T> type) {
        if (name != null && name.startsWith("#class:")) {
            return createBean(name, type);
        } else if (name != null && name.startsWith("#type:")) {
            return lookupBean(name, type);
        } else {
            return lookupByNameAndType(name, type);
        }
    }

    public <T> T lookupByNameAndType(String name, Class<T> type) {
        if (name != null && name.startsWith("#class:")) {
            return createBean(name, type);
        } else if (name != null && name.startsWith("#type:")) {
            return lookupBean(name, type);
        } else {
            return getRegistry().lookupByNameAndType(name, type);
        }
    }

    @Override
    public <T> Map<String, T> findByTypeWithName(Class<T> type) {
        return getRegistry().findByTypeWithName(type);
    }

    @Override
    public <T> Set<T> findByType(Class<T> type) {
        return getRegistry().findByType(type);
    }

    @Override
    public Object unwrap(Object value) {
        return getRegistry().unwrap(value);
    }

    public Endpoint resolveEndpoint(String uri) throws NoSuchEndpointException {
        return CamelContextHelper.getMandatoryEndpoint(camelContext, uri);
    }

    private <T> T createBean(String name, Class<T> type) {
        try {
            return doCreateBean(name, type);
        } catch (Exception e) {
            throw RuntimeCamelException.wrapRuntimeException(e);
        }
    }

    private <T> T doCreateBean(String name, Class<T> type) throws Exception {
        Object answer;

        // if there is a factory method then the class/bean should be created in a different way
        String className;
        String factoryMethod = null;
        String parameters = null;
        className = name.substring(7);
        if (className.endsWith(")") && className.indexOf('(') != -1) {
            parameters = StringHelper.after(className, "(");
            parameters = parameters.substring(0, parameters.length() - 1); // clip last )
            className = StringHelper.before(className, "(");
        }
        if (className != null && className.indexOf('#') != -1) {
            factoryMethod = StringHelper.after(className, "#");
            className = StringHelper.before(className, "#");
        }
        Class<?> clazz = camelContext.getClassResolver().resolveMandatoryClass(className);

        if (factoryMethod != null && parameters != null) {
            answer = PropertyBindingSupport.newInstanceFactoryParameters(camelContext, clazz, factoryMethod, parameters);
        } else if (factoryMethod != null) {
            answer = camelContext.getInjector().newInstance(type, factoryMethod);
        } else if (parameters != null) {
            answer = PropertyBindingSupport.newInstanceConstructorParameters(camelContext, clazz, parameters);
        } else {
            answer = camelContext.getInjector().newInstance(clazz);
        }
        if (answer == null) {
            throw new IllegalStateException("Cannot create bean: " + name);
        }
        return type.cast(answer);
    }

    private <T> T lookupBean(String name, Class<T> type) {
        try {
            return doLookupBean(name, type);
        } catch (Exception e) {
            throw RuntimeCamelException.wrapRuntimeException(e);
        }
    }

    private <T> T doLookupBean(String name, Class<T> type) throws ClassNotFoundException {
        Class<?> clazz = camelContext.getClassResolver().resolveMandatoryClass(name.substring(6));
        Set<?> found = getRegistry().findByType(clazz);
        if (found == null || found.isEmpty()) {
            throw new NoSuchBeanException(null, clazz.getName());
        } else if (found.size() > 1) {
            throw new NoSuchBeanException(
                    "Found " + found.size() + " beans of type: " + clazz + ". Only one bean expected.");
        } else {
            Object answer = found.iterator().next();
            return type.cast(answer);
        }
    }

}

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
package org.apache.camel.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.camel.CamelContext;
import org.apache.camel.spi.Metadata;

/**
 * To refer to an XML file with route configuration defined using the xml-dsl
 */
@Metadata(label = "configuration")
@XmlRootElement(name = "routeConfigurationContextRef")
@XmlAccessorType(XmlAccessType.FIELD)
public class RouteConfigurationContextRefDefinition {
    @XmlAttribute(required = true)
    private String ref;

    public RouteConfigurationContextRefDefinition() {
    }

    public RouteConfigurationContextRefDefinition(String ref) {
        this.ref = ref;
    }

    @Override
    public String toString() {
        return "RouteConfigurationContextRef[" + getRef() + "]";
    }

    public String getRef() {
        return ref;
    }

    /**
     * Reference to the route templates in the xml dsl
     */
    public void setRef(String ref) {
        this.ref = ref;
    }

    public List<RouteConfigurationDefinition> lookupRouteConfigurations(CamelContext camelContext) {
        return RouteConfigurationContextRefDefinitionHelper.lookupRouteConfigurations(camelContext, ref);
    }

}

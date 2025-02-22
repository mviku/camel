/* Generated by camel build tools - do NOT edit this file! */
package org.apache.camel.component.telegram;

import java.net.URISyntaxException;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.camel.spi.EndpointUriFactory;

/**
 * Generated by camel build tools - do NOT edit this file!
 */
public class TelegramEndpointUriFactory extends org.apache.camel.support.component.EndpointUriFactorySupport implements EndpointUriFactory {

    private static final String BASE = ":type";

    private static final Set<String> PROPERTY_NAMES;
    private static final Set<String> SECRET_PROPERTY_NAMES;
    private static final Set<String> MULTI_VALUE_PREFIXES;
    static {
        Set<String> props = new HashSet<>(31);
        props.add("backoffMultiplier");
        props.add("chatId");
        props.add("initialDelay");
        props.add("type");
        props.add("timeout");
        props.add("scheduler");
        props.add("authorizationToken");
        props.add("proxyPort");
        props.add("bridgeErrorHandler");
        props.add("useFixedDelay");
        props.add("runLoggingLevel");
        props.add("backoffErrorThreshold");
        props.add("limit");
        props.add("greedy");
        props.add("scheduledExecutorService");
        props.add("repeatCount");
        props.add("timeUnit");
        props.add("sendEmptyMessageWhenIdle");
        props.add("schedulerProperties");
        props.add("exchangePattern");
        props.add("proxyType");
        props.add("clientConfig");
        props.add("proxyHost");
        props.add("backoffIdleThreshold");
        props.add("lazyStartProducer");
        props.add("delay");
        props.add("pollStrategy");
        props.add("startScheduler");
        props.add("baseUri");
        props.add("exceptionHandler");
        props.add("bufferSize");
        PROPERTY_NAMES = Collections.unmodifiableSet(props);
        Set<String> secretProps = new HashSet<>(1);
        secretProps.add("authorizationToken");
        SECRET_PROPERTY_NAMES = Collections.unmodifiableSet(secretProps);
        Set<String> prefixes = new HashSet<>(1);
        prefixes.add("scheduler.");
        MULTI_VALUE_PREFIXES = Collections.unmodifiableSet(prefixes);
    }

    @Override
    public boolean isEnabled(String scheme) {
        return "telegram".equals(scheme);
    }

    @Override
    public String buildUri(String scheme, Map<String, Object> properties, boolean encode) throws URISyntaxException {
        String syntax = scheme + BASE;
        String uri = syntax;

        Map<String, Object> copy = new HashMap<>(properties);

        uri = buildPathParameter(syntax, uri, "type", null, true, copy);
        uri = buildQueryParameters(uri, copy, encode);
        return uri;
    }

    @Override
    public Set<String> propertyNames() {
        return PROPERTY_NAMES;
    }

    @Override
    public Set<String> secretPropertyNames() {
        return SECRET_PROPERTY_NAMES;
    }

    @Override
    public Set<String> multiValuePrefixes() {
        return MULTI_VALUE_PREFIXES;
    }

    @Override
    public boolean isLenientProperties() {
        return false;
    }
}


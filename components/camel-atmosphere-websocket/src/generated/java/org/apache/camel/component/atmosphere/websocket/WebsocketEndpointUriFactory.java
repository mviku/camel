/* Generated by camel build tools - do NOT edit this file! */
package org.apache.camel.component.atmosphere.websocket;

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
public class WebsocketEndpointUriFactory extends org.apache.camel.support.component.EndpointUriFactorySupport implements EndpointUriFactory {

    private static final String BASE = ":servicePath";

    private static final Set<String> PROPERTY_NAMES;
    private static final Set<String> SECRET_PROPERTY_NAMES;
    private static final Set<String> MULTI_VALUE_PREFIXES;
    static {
        Set<String> props = new HashSet<>(27);
        props.add("sendToAll");
        props.add("attachmentMultipartBinding");
        props.add("mapHttpMessageBody");
        props.add("servletName");
        props.add("useStreaming");
        props.add("bridgeErrorHandler");
        props.add("headerFilterStrategy");
        props.add("transferException");
        props.add("bridgeEndpoint");
        props.add("muteException");
        props.add("httpMethodRestrict");
        props.add("eagerCheckContentAvailable");
        props.add("httpBinding");
        props.add("matchOnUriPrefix");
        props.add("exchangePattern");
        props.add("chunked");
        props.add("mapHttpMessageFormUrlEncodedBody");
        props.add("fileNameExtWhitelist");
        props.add("async");
        props.add("responseBufferSize");
        props.add("lazyStartProducer");
        props.add("disableStreamCache");
        props.add("servicePath");
        props.add("mapHttpMessageHeaders");
        props.add("optionsEnabled");
        props.add("traceEnabled");
        props.add("exceptionHandler");
        PROPERTY_NAMES = Collections.unmodifiableSet(props);
        SECRET_PROPERTY_NAMES = Collections.emptySet();
        MULTI_VALUE_PREFIXES = Collections.emptySet();
    }

    @Override
    public boolean isEnabled(String scheme) {
        return "atmosphere-websocket".equals(scheme);
    }

    @Override
    public String buildUri(String scheme, Map<String, Object> properties, boolean encode) throws URISyntaxException {
        String syntax = scheme + BASE;
        String uri = syntax;

        Map<String, Object> copy = new HashMap<>(properties);

        uri = buildPathParameter(syntax, uri, "servicePath", null, true, copy);
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


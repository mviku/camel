/* Generated by camel build tools - do NOT edit this file! */
package org.apache.camel.component.vertx.http;

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
public class VertxHttpEndpointUriFactory extends org.apache.camel.support.component.EndpointUriFactorySupport implements EndpointUriFactory {

    private static final String BASE = ":httpUri";

    private static final Set<String> PROPERTY_NAMES;
    private static final Set<String> SECRET_PROPERTY_NAMES;
    private static final Set<String> MULTI_VALUE_PREFIXES;
    static {
        Set<String> props = new HashSet<>(24);
        props.add("throwExceptionOnFailure");
        props.add("proxyUsername");
        props.add("sessionManagement");
        props.add("webClientOptions");
        props.add("proxyPassword");
        props.add("proxyType");
        props.add("basicAuthPassword");
        props.add("sslContextParameters");
        props.add("httpMethod");
        props.add("useCompression");
        props.add("vertxHttpBinding");
        props.add("okStatusCodeRange");
        props.add("responsePayloadAsByteArray");
        props.add("proxyHost");
        props.add("timeout");
        props.add("proxyPort");
        props.add("lazyStartProducer");
        props.add("bearerToken");
        props.add("basicAuthUsername");
        props.add("httpUri");
        props.add("headerFilterStrategy");
        props.add("transferException");
        props.add("connectTimeout");
        props.add("cookieStore");
        PROPERTY_NAMES = Collections.unmodifiableSet(props);
        SECRET_PROPERTY_NAMES = Collections.emptySet();
        MULTI_VALUE_PREFIXES = Collections.emptySet();
    }

    @Override
    public boolean isEnabled(String scheme) {
        return "vertx-http".equals(scheme);
    }

    @Override
    public String buildUri(String scheme, Map<String, Object> properties, boolean encode) throws URISyntaxException {
        String syntax = scheme + BASE;
        String uri = syntax;

        Map<String, Object> copy = new HashMap<>(properties);

        uri = buildPathParameter(syntax, uri, "httpUri", null, true, copy);
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
        return true;
    }
}


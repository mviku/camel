/* Generated by camel build tools - do NOT edit this file! */
package org.apache.camel.component.mail;

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
public class MailEndpointUriFactory extends org.apache.camel.support.component.EndpointUriFactorySupport implements EndpointUriFactory {

    private static final String BASE = ":host:port";
    private static final String[] SCHEMES = new String[]{"imap", "imaps", "pop3", "pop3s", "smtp", "smtps"};

    private static final Set<String> PROPERTY_NAMES;
    private static final Set<String> SECRET_PROPERTY_NAMES;
    private static final Set<String> MULTI_VALUE_PREFIXES;
    static {
        Set<String> props = new HashSet<>(68);
        props.add("disconnect");
        props.add("contentTypeResolver");
        props.add("subject");
        props.add("binding");
        props.add("initialDelay");
        props.add("copyTo");
        props.add("password");
        props.add("bridgeErrorHandler");
        props.add("searchTerm");
        props.add("alternativeBodyHeader");
        props.add("mimeDecodeHeaders");
        props.add("host");
        props.add("attachmentsContentTransferEncodingResolver");
        props.add("skipFailedMessage");
        props.add("greedy");
        props.add("maxMessagesPerPoll");
        props.add("from");
        props.add("scheduledExecutorService");
        props.add("contentType");
        props.add("repeatCount");
        props.add("postProcessAction");
        props.add("sendEmptyMessageWhenIdle");
        props.add("schedulerProperties");
        props.add("sortTerm");
        props.add("idempotentRepositoryRemoveOnCommit");
        props.add("backoffIdleThreshold");
        props.add("lazyStartProducer");
        props.add("delay");
        props.add("port");
        props.add("startScheduler");
        props.add("replyTo");
        props.add("mailUidGenerator");
        props.add("folderName");
        props.add("exceptionHandler");
        props.add("debugMode");
        props.add("backoffMultiplier");
        props.add("bcc");
        props.add("session");
        props.add("sslContextParameters");
        props.add("mapMailMessage");
        props.add("delete");
        props.add("handleFailedMessage");
        props.add("scheduler");
        props.add("closeFolder");
        props.add("additionalJavaMailProperties");
        props.add("useFixedDelay");
        props.add("headerFilterStrategy");
        props.add("runLoggingLevel");
        props.add("backoffErrorThreshold");
        props.add("authenticator");
        props.add("connectionTimeout");
        props.add("timeUnit");
        props.add("cc");
        props.add("fetchSize");
        props.add("javaMailSender");
        props.add("useInlineAttachments");
        props.add("idempotentRepository");
        props.add("exchangePattern");
        props.add("ignoreUnsupportedCharset");
        props.add("ignoreUriScheme");
        props.add("peek");
        props.add("pollStrategy");
        props.add("decodeFilename");
        props.add("to");
        props.add("javaMailProperties");
        props.add("unseen");
        props.add("moveTo");
        props.add("username");
        PROPERTY_NAMES = Collections.unmodifiableSet(props);
        Set<String> secretProps = new HashSet<>(2);
        secretProps.add("password");
        secretProps.add("username");
        SECRET_PROPERTY_NAMES = Collections.unmodifiableSet(secretProps);
        Set<String> prefixes = new HashSet<>(3);
        prefixes.add("searchTerm.");
        prefixes.add("mail.");
        prefixes.add("scheduler.");
        MULTI_VALUE_PREFIXES = Collections.unmodifiableSet(prefixes);
    }

    @Override
    public boolean isEnabled(String scheme) {
        for (String s : SCHEMES) {
            if (s.equals(scheme)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String buildUri(String scheme, Map<String, Object> properties, boolean encode) throws URISyntaxException {
        String syntax = scheme + BASE;
        String uri = syntax;

        Map<String, Object> copy = new HashMap<>(properties);

        uri = buildPathParameter(syntax, uri, "host", null, true, copy);
        uri = buildPathParameter(syntax, uri, "port", null, false, copy);
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


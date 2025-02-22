/* Generated by camel build tools - do NOT edit this file! */
package org.apache.camel.component.kafka;

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
public class KafkaEndpointUriFactory extends org.apache.camel.support.component.EndpointUriFactorySupport implements EndpointUriFactory {

    private static final String BASE = ":topic";

    private static final Set<String> PROPERTY_NAMES;
    private static final Set<String> SECRET_PROPERTY_NAMES;
    private static final Set<String> MULTI_VALUE_PREFIXES;
    static {
        Set<String> props = new HashSet<>(103);
        props.add("synchronous");
        props.add("queueBufferingMaxMessages");
        props.add("allowManualCommit");
        props.add("consumersCount");
        props.add("receiveBufferBytes");
        props.add("reconnectBackoffMaxMs");
        props.add("valueDeserializer");
        props.add("metricReporters");
        props.add("sslTruststoreType");
        props.add("sendBufferBytes");
        props.add("heartbeatIntervalMs");
        props.add("interceptorClasses");
        props.add("sslKeystoreType");
        props.add("breakOnFirstError");
        props.add("requestRequiredAcks");
        props.add("enableIdempotence");
        props.add("pollOnError");
        props.add("fetchWaitMaxMs");
        props.add("retries");
        props.add("maxPollRecords");
        props.add("additionalProperties");
        props.add("keyDeserializer");
        props.add("producerBatchSize");
        props.add("retryBackoffMs");
        props.add("brokers");
        props.add("metricsSampleWindowMs");
        props.add("sslContextParameters");
        props.add("sslKeyPassword");
        props.add("keySerializer");
        props.add("noOfMetricsSample");
        props.add("maxPartitionFetchBytes");
        props.add("partitionKey");
        props.add("headerFilterStrategy");
        props.add("sslTruststorePassword");
        props.add("sessionTimeoutMs");
        props.add("key");
        props.add("topicIsPattern");
        props.add("sslTruststoreLocation");
        props.add("clientId");
        props.add("maxRequestSize");
        props.add("recordMetadata");
        props.add("sslTrustmanagerAlgorithm");
        props.add("compressionCodec");
        props.add("autoCommitOnStop");
        props.add("commitTimeoutMs");
        props.add("workerPoolCoreSize");
        props.add("autoCommitEnable");
        props.add("consumerRequestTimeoutMs");
        props.add("maxPollIntervalMs");
        props.add("kerberosInitCmd");
        props.add("workerPoolMaxSize");
        props.add("reconnectBackoffMs");
        props.add("groupId");
        props.add("offsetRepository");
        props.add("kerberosRenewJitter");
        props.add("sslProvider");
        props.add("saslKerberosServiceName");
        props.add("bridgeErrorHandler");
        props.add("shutdownTimeout");
        props.add("saslMechanism");
        props.add("workerPool");
        props.add("deliveryTimeoutMs");
        props.add("lazyStartProducer");
        props.add("sslKeystorePassword");
        props.add("kafkaManualCommitFactory");
        props.add("sslEndpointAlgorithm");
        props.add("resumeStrategy");
        props.add("topic");
        props.add("sslProtocol");
        props.add("sslKeymanagerAlgorithm");
        props.add("pollTimeoutMs");
        props.add("exceptionHandler");
        props.add("maxBlockMs");
        props.add("kerberosBeforeReloginMinTime");
        props.add("groupInstanceId");
        props.add("bufferMemorySize");
        props.add("metadataMaxAgeMs");
        props.add("sslCipherSuites");
        props.add("specificAvroReader");
        props.add("saslJaasConfig");
        props.add("fetchMinBytes");
        props.add("connectionMaxIdleMs");
        props.add("lingerMs");
        props.add("kerberosRenewWindowFactor");
        props.add("securityProtocol");
        props.add("autoCommitIntervalMs");
        props.add("partitioner");
        props.add("kerberosPrincipalToLocalRules");
        props.add("headerSerializer");
        props.add("sslEnabledProtocols");
        props.add("sslKeystoreLocation");
        props.add("schemaRegistryURL");
        props.add("headerDeserializer");
        props.add("maxInFlightRequest");
        props.add("exchangePattern");
        props.add("valueSerializer");
        props.add("autoOffsetReset");
        props.add("seekTo");
        props.add("kafkaClientFactory");
        props.add("requestTimeoutMs");
        props.add("fetchMaxBytes");
        props.add("checkCrcs");
        props.add("partitionAssignor");
        PROPERTY_NAMES = Collections.unmodifiableSet(props);
        Set<String> secretProps = new HashSet<>(4);
        secretProps.add("sslKeystorePassword");
        secretProps.add("sslTruststorePassword");
        secretProps.add("saslJaasConfig");
        secretProps.add("sslKeyPassword");
        SECRET_PROPERTY_NAMES = Collections.unmodifiableSet(secretProps);
        Set<String> prefixes = new HashSet<>(1);
        prefixes.add("additionalProperties.");
        MULTI_VALUE_PREFIXES = Collections.unmodifiableSet(prefixes);
    }

    @Override
    public boolean isEnabled(String scheme) {
        return "kafka".equals(scheme);
    }

    @Override
    public String buildUri(String scheme, Map<String, Object> properties, boolean encode) throws URISyntaxException {
        String syntax = scheme + BASE;
        String uri = syntax;

        Map<String, Object> copy = new HashMap<>(properties);

        uri = buildPathParameter(syntax, uri, "topic", null, true, copy);
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


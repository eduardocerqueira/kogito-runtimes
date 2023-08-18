package org.kie.kogito.addons.quarkus.knative.eventing;

import org.eclipse.microprofile.config.spi.ConfigSource;
import org.junit.jupiter.api.Test;

import io.smallrye.config.ConfigSourceContext;
import io.smallrye.config.ConfigValue;

import static org.assertj.core.api.Assertions.assertThat;
import static org.kie.kogito.addons.quarkus.knative.eventing.KnativeEventingConfigSource.K_SINK;
import static org.kie.kogito.addons.quarkus.knative.eventing.KnativeEventingConfigSourceFactory.INCLUDE_PROCESS_EVENTS;
import static org.kie.kogito.addons.quarkus.knative.eventing.KnativeEventingConfigSourceFactory.SKIP_DEFAULT_INCOMING_STREAM;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

class KnativeEventingConfigSourceFactoryTest {

    private static String DEFAULT_SINK_CONFIG = "${K_SINK:http://localhost:9090}";

    @Test
    void getConfigSourcesWithProcessEventsAndDefaultIncomeStream() {
        KnativeEventingConfigSource eventingConfigSource = buildKnativeEventingConfigSource("true", null);

        assertThat(eventingConfigSource.getPropertyNames()).hasSize(10);
        assertProcessEvents(eventingConfigSource);
        assertDefaultIncomingConnector(eventingConfigSource);
        assertDefaultOutgoingConnector(eventingConfigSource);
    }

    @Test
    void getConfigSourcesWithProcessEvents() {
        KnativeEventingConfigSource eventingConfigSource = buildKnativeEventingConfigSource("true", "true");

        assertThat(eventingConfigSource.getPropertyNames()).hasSize(8);
        assertProcessEvents(eventingConfigSource);
        assertDefaultOutgoingConnector(eventingConfigSource);
    }

    @Test
    void getConfigSourcesWithDefaultIncomeStream() {
        KnativeEventingConfigSource eventingConfigSource = buildKnativeEventingConfigSource(null, null);

        assertThat(eventingConfigSource.getPropertyNames()).hasSize(4);
        assertDefaultOutgoingConnector(eventingConfigSource);
    }

    private static KnativeEventingConfigSource buildKnativeEventingConfigSource(String includeProcessEvents,
            String skipDefaultIncomingStream) {
        ConfigSourceContext context = mock(ConfigSourceContext.class);
        mockConfigValue(context, INCLUDE_PROCESS_EVENTS, includeProcessEvents);
        mockConfigValue(context, SKIP_DEFAULT_INCOMING_STREAM, skipDefaultIncomingStream);
        mockConfigValue(context, K_SINK, null);

        ConfigSource configSource = new KnativeEventingConfigSourceFactory()
                .getConfigSources(context)
                .iterator()
                .next();
        assertThat(configSource).isExactlyInstanceOf(KnativeEventingConfigSource.class);
        KnativeEventingConfigSource eventingConfigSource = (KnativeEventingConfigSource) configSource;
        assertThat(eventingConfigSource.getName()).isEqualTo("KnativeEventingConfigSource");
        return eventingConfigSource;
    }

    private static void mockConfigValue(ConfigSourceContext context, String name, String value) {
        ConfigValue configValue = mock(ConfigValue.class);
        doReturn(value).when(configValue).getValue();
        doReturn(configValue).when(context).getValue(name);
    }

    private static void assertProcessEvents(KnativeEventingConfigSource eventingConfigSource) {
        assertContainsProperty(eventingConfigSource, "mp.messaging.outgoing.kogito-processinstances-events.connector", "quarkus-http");
        assertContainsProperty(eventingConfigSource, "mp.messaging.outgoing.kogito-processinstances-events.url", DEFAULT_SINK_CONFIG);

        assertContainsProperty(eventingConfigSource, "mp.messaging.outgoing.kogito-usertaskinstances-events.connector", "quarkus-http");
        assertContainsProperty(eventingConfigSource, "mp.messaging.outgoing.kogito-usertaskinstances-events.url", DEFAULT_SINK_CONFIG);

        assertContainsProperty(eventingConfigSource, "mp.messaging.outgoing.kogito-variables-events.connector", "quarkus-http");
        assertContainsProperty(eventingConfigSource, "mp.messaging.outgoing.kogito-variables-events.url", DEFAULT_SINK_CONFIG);
    }

    private static void assertDefaultIncomingConnector(KnativeEventingConfigSource eventingConfigSource) {
        assertContainsProperty(eventingConfigSource, "mp.messaging.incoming.kogito_incoming_stream.connector", "quarkus-http");
        assertContainsProperty(eventingConfigSource, "mp.messaging.incoming.kogito_incoming_stream.path", "/");
    }

    private static void assertDefaultOutgoingConnector(KnativeEventingConfigSource eventingConfigSource) {
        assertContainsProperty(eventingConfigSource, "mp.messaging.outgoing.kogito_outgoing_stream.connector", "quarkus-http");
        assertContainsProperty(eventingConfigSource, "mp.messaging.outgoing.kogito_outgoing_stream.url", DEFAULT_SINK_CONFIG);
    }

    private static void assertContainsProperty(KnativeEventingConfigSource eventingConfigSource,
            String propertyName,
            String propertyValue) {
        assertThat(eventingConfigSource.getPropertyNames()).contains(propertyName);
        assertThat(eventingConfigSource.getValue(propertyName)).isEqualTo(propertyValue);
    }
}

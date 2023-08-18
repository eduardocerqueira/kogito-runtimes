package org.kie.kogito.addons.quarkus.knative.eventing;

import java.util.Map;
import java.util.Set;

import org.eclipse.microprofile.config.spi.ConfigSource;

/**
 * Provides the default configuration for a Kogito project that uses Knative Eventing as underling event platform
 */
public class KnativeEventingConfigSource implements ConfigSource {

    /**
     * Environment variable injected by Knative
     */
    public static final String K_SINK = "K_SINK";

    static final Integer ORDINAL = Integer.MIN_VALUE;

    private final Map<String, String> configuration;

    public KnativeEventingConfigSource(Map<String, String> configuration) {
        this.configuration = configuration;
    }

    @Override
    public int getOrdinal() {
        return ORDINAL;
    }

    @Override
    public Set<String> getPropertyNames() {
        return configuration.keySet();
    }

    @Override
    public String getValue(String propertyName) {
        return configuration.get(propertyName);
    }

    @Override
    public String getName() {
        return KnativeEventingConfigSource.class.getSimpleName();
    }
}

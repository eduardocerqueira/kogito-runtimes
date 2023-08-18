package org.kie.kogito.addons.quarkus.knative.eventing;

import java.util.Set;

import org.eclipse.microprofile.config.spi.ConfigSource;

/**
 * Creates an alias ({@value CONFIG_ALIAS}) for the
 * {@code "io.smallrye.health.check."org.kie.kogito.addons.quarkus.knative.eventing.KSinkInjectionHealthCheck".enabled"} property.
 */
class KSinkInjectionHealthCheckConfigSource implements ConfigSource {

    private static final String CONFIG_ALIAS = org.kie.kogito.addons.quarkus.knative.eventing.KSinkInjectionHealthCheck.CONFIG_ALIAS;

    private final String configValue;

    private final int ordinal;

    private static final Set<String> propertyNames = Set.of(
            "io.smallrye.health.check." + KSinkInjectionHealthCheck.class.getName() + ".enabled",
            CONFIG_ALIAS);

    public KSinkInjectionHealthCheckConfigSource(int ordinal, String configValue) {
        this.ordinal = ordinal;
        this.configValue = configValue;
    }

    @Override
    public Set<String> getPropertyNames() {
        return propertyNames;
    }

    @Override
    public String getValue(String propertyName) {
        return propertyNames.contains(propertyName) ? configValue : null;
    }

    @Override
    public String getName() {
        return KSinkInjectionHealthCheckConfigSource.class.getSimpleName();
    }

    @Override
    public int getOrdinal() {
        return ordinal;
    }
}

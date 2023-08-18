package org.kie.kogito.addons.quarkus.knative.eventing;

import java.util.List;
import java.util.OptionalInt;

import org.eclipse.microprofile.config.spi.ConfigSource;

import io.smallrye.config.ConfigSourceContext;
import io.smallrye.config.ConfigSourceFactory;
import io.smallrye.config.ConfigValue;

import static org.kie.kogito.addons.quarkus.knative.eventing.KSinkInjectionHealthCheck.CONFIG_ALIAS;

public final class KSinkInjectionHealthCheckConfigSourceFactory implements ConfigSourceFactory {

    @Override
    public Iterable<ConfigSource> getConfigSources(ConfigSourceContext context) {
        ConfigValue configValue = context.getValue(CONFIG_ALIAS);
        String value = configValue.getValue();
        if (value == null || value.isEmpty()) {
            return List.of();
        } else {
            return List.of(new KSinkInjectionHealthCheckConfigSource(configValue.getSourceOrdinal(), value));
        }
    }

    @Override
    public OptionalInt getPriority() {
        return OptionalInt.of(Integer.MAX_VALUE);
    }
}

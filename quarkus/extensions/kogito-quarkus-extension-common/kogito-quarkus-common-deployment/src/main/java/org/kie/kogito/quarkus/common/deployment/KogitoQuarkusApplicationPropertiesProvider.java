package org.kie.kogito.quarkus.common.deployment;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.StreamSupport;

import org.eclipse.microprofile.config.ConfigProvider;
import org.kie.kogito.codegen.api.context.KogitoApplicationPropertyProvider;

import static java.util.stream.Collectors.toSet;

public class KogitoQuarkusApplicationPropertiesProvider implements KogitoApplicationPropertyProvider {

    @Override
    public Optional<String> getApplicationProperty(String property) {
        return ConfigProvider.getConfig().getOptionalValue(property, String.class);
    }

    @Override
    public Collection<String> getApplicationProperties() {
        return StreamSupport.stream(ConfigProvider.getConfig().getPropertyNames().spliterator(), false).collect(toSet());
    }

    @Override
    public <T> Optional<T> getApplicationProperty(String property, Class<T> clazz) {
        return ConfigProvider.getConfig().getOptionalValue(property, clazz);
    }

    @Override
    public void setApplicationProperty(String key, String value) {
        System.setProperty(key, value);
    }
}

package org.kie.kogito.codegen.api.context;

import java.util.Collection;
import java.util.Optional;
import java.util.Properties;

import static org.kie.kogito.internal.utils.ConversionUtils.convert;

public interface KogitoApplicationPropertyProvider {

    static KogitoApplicationPropertyProvider of(Properties properties) {
        return new KogitoApplicationPropertyProvider() {
            @Override
            public Optional<String> getApplicationProperty(String property) {
                return Optional.ofNullable(properties.getProperty(property));
            }

            @Override
            public Collection<String> getApplicationProperties() {
                return properties.stringPropertyNames();
            }

            @Override
            public void setApplicationProperty(String key, String value) {
                properties.put(key, value);
            }

            @Override
            public <T> Optional<T> getApplicationProperty(String property, Class<T> clazz) {
                return Optional.ofNullable(convert(properties.getProperty(property), clazz));
            }
        };
    }

    Optional<String> getApplicationProperty(String property);

    <T> Optional<T> getApplicationProperty(String property, Class<T> clazz);

    Collection<String> getApplicationProperties();

    void setApplicationProperty(String key, String value);
}

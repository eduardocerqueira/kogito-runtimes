package org.kie.kogito.secret;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.ConfigProvider;
import org.kie.kogito.serverless.workflow.utils.ConfigResolver;

import io.quarkus.runtime.Startup;
import io.smallrye.config.SecretKeys;

@Startup
public class QuarkusConfigResolver implements ConfigResolver {

    private Config config;

    public QuarkusConfigResolver() {
        config = ConfigProvider.getConfig();
    }

    @Override
    public <T> Optional<T> getConfigProperty(String key, Class<T> clazz) {
        try {
            return config.getOptionalValue(key, clazz);
        } catch (SecurityException ex) {
            // see https://smallrye.io/docs/smallrye-config/config/secret-keys.html
            return SecretKeys.doUnlocked(() -> config.getOptionalValue(key, clazz));
        }
    }

    @Override
    public Iterable<String> getPropertyNames() {
        return config.getPropertyNames();
    }

    @Override
    public <T> Collection<T> getIndexedConfigProperty(String key, Class<T> clazz) {
        try {
            return config.getOptionalValues(key, clazz).orElse(Collections.emptyList());
        } catch (SecurityException ex) {
            // see https://smallrye.io/docs/smallrye-config/config/secret-keys.html
            return SecretKeys.doUnlocked(() -> config.getOptionalValues(key, clazz)).orElse(Collections.emptyList());
        }
    }
}

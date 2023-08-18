package org.kie.kogito.addons.quarkus.k8s.config;

import java.net.URI;
import java.util.Optional;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class KubeDiscoveryConfigCacheTest {

    @ParameterizedTest
    @ValueSource(strings = { "a_non_valid_uri", "knative", "kubernetes", "openshift" })
    void nonValidURIShouldNotBeCached(String nonValidURI) {
        KubeDiscoveryConfigCache kubeDiscoveryConfigCache = new KubeDiscoveryConfigCache(null);
        Optional<String> cachedValue = kubeDiscoveryConfigCache.get("my_config", nonValidURI);

        assertThat(cachedValue).hasValue(nonValidURI);
    }

    @ParameterizedTest
    @ValueSource(strings = { "kubernetes:services.v1/myservice", "openshift:services.v1/myservice" })
    void validURIShouldBeCached(String validURI) {
        String cachedUri = "cached_uri";
        KubeDiscoveryConfigCacheUpdater kResource = new ConstantKubeDiscoveryConfigCacheUpdater(cachedUri);
        KubeDiscoveryConfigCache kubeDiscoveryConfigCache = new KubeDiscoveryConfigCache(kResource);
        Optional<String> cachedValue = kubeDiscoveryConfigCache.get("my_config", validURI);

        assertThat(cachedValue).hasValue(cachedUri);
    }

    /**
     * Class that always returns the same value.
     */
    private static class ConstantKubeDiscoveryConfigCacheUpdater extends KubeDiscoveryConfigCacheUpdater {

        private final String alwaysReturnedValue;

        public ConstantKubeDiscoveryConfigCacheUpdater(String alwaysReturnedValue) {
            super(null);
            this.alwaysReturnedValue = alwaysReturnedValue;
        }

        @Override
        public Optional<URI> update(String configValue) {
            return Optional.of(URI.create(alwaysReturnedValue));
        }
    }
}

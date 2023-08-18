package org.kie.kogito.addons.quarkus.k8s.config;

import java.text.MessageFormat;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.kie.kogito.addons.k8s.resource.catalog.KubernetesServiceCatalog;
import org.kie.kogito.addons.k8s.resource.catalog.KubernetesServiceCatalogProvider;

import static java.util.stream.Collectors.joining;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.kie.kogito.addons.quarkus.k8s.config.KubeDiscoveryConfigSourceInterceptor.MULTIPLE_PROVIDERS_FOUND_MSG;

class KubeDiscoveryConfigSourceInterceptorTest {

    @Test
    void shouldNotThrowExceptionWhenOneProviderIsPresent() {
        assertThatCode(() -> KubeDiscoveryConfigSourceInterceptor.createKubernetesServiceCatalog(List.of(new TestKubernetesServiceCatalogProvider())))
                .doesNotThrowAnyException();
    }

    @Test
    void shouldNotThrowExceptionWhenNoneProviderIsPresent() {
        assertThatCode(() -> KubeDiscoveryConfigSourceInterceptor.createKubernetesServiceCatalog(List.of()))
                .doesNotThrowAnyException();
    }

    @Test
    void shouldThrowExceptionWhenMoreThanOneProviderIsPresent() {
        List<KubernetesServiceCatalogProvider> providers = List.of(
                new TestKubernetesServiceCatalogProvider(), new TestKubernetesServiceCatalogProvider());

        String providersName = providers.stream()
                .map(provider -> provider.getClass().getName())
                .collect(joining(", ", "[", "]"));

        String expectedMessage = MessageFormat.format(MULTIPLE_PROVIDERS_FOUND_MSG, KubernetesServiceCatalogProvider.class,
                providersName);

        assertThatExceptionOfType(IllegalStateException.class)
                .isThrownBy(() -> KubeDiscoveryConfigSourceInterceptor.createKubernetesServiceCatalog(providers))
                .withMessage(expectedMessage);
    }

    private static class TestKubernetesServiceCatalogProvider implements KubernetesServiceCatalogProvider {

        @Override
        public KubernetesServiceCatalog create() {
            return null;
        }
    }
}
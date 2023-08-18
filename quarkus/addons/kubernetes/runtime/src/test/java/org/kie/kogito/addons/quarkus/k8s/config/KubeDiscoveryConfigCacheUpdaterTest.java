package org.kie.kogito.addons.quarkus.k8s.config;

import java.net.URI;

import javax.inject.Inject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kie.kogito.addons.k8s.resource.catalog.KubernetesServiceCatalog;

import io.fabric8.kubernetes.client.server.mock.KubernetesServer;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.kubernetes.client.KubernetesTestServer;
import io.quarkus.test.kubernetes.client.WithKubernetesTestServer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.kie.kogito.addons.quarkus.k8s.test.utils.KnativeResourceDiscoveryTestUtil.createServiceIfNotExists;

@QuarkusTest
@WithKubernetesTestServer
class KubeDiscoveryConfigCacheUpdaterTest {

    @KubernetesTestServer
    static KubernetesServer mockServer;

    KubeDiscoveryConfigCacheUpdater kubeDiscoveryConfigCacheUpdater;

    final String remoteServiceUrl = "http://serverless-workflow-greeting-quarkus.test.10.99.154.147.sslip.io";

    @Inject
    KubernetesServiceCatalog kubernetesServiceCatalog;

    @BeforeEach
    void beforeEach() {
        createServiceIfNotExists(mockServer, "knative/quarkus-greeting.yaml", "test", "serverless-workflow-greeting-quarkus", remoteServiceUrl);
        kubeDiscoveryConfigCacheUpdater = new KubeDiscoveryConfigCacheUpdater(kubernetesServiceCatalog);
    }

    @Test
    void knativeService() {
        assertThat(kubeDiscoveryConfigCacheUpdater.update("knative:test/serverless-workflow-greeting-quarkus"))
                .hasValue(URI.create(remoteServiceUrl));
    }

    @Test
    void knativeResource() {
        assertThat(kubeDiscoveryConfigCacheUpdater.update("knative:services.v1.serving.knative.dev/test/serverless-workflow-greeting-quarkus"))
                .hasValue(URI.create(remoteServiceUrl));
    }
}
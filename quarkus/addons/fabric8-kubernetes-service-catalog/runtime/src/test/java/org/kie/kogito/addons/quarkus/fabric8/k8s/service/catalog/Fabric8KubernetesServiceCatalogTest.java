package org.kie.kogito.addons.quarkus.fabric8.k8s.service.catalog;

import javax.inject.Inject;

import org.junit.jupiter.api.BeforeEach;
import org.kie.kogito.addons.k8s.resource.catalog.KubernetesServiceCatalogTest;

import io.fabric8.kubernetes.client.server.mock.KubernetesServer;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.kubernetes.client.KubernetesTestServer;
import io.quarkus.test.kubernetes.client.WithKubernetesTestServer;

import static org.kie.kogito.addons.quarkus.k8s.test.utils.KnativeResourceDiscoveryTestUtil.createServiceIfNotExists;

@QuarkusTest
@WithKubernetesTestServer
class Fabric8KubernetesServiceCatalogTest extends KubernetesServiceCatalogTest {

    @KubernetesTestServer
    KubernetesServer mockServer;

    @BeforeEach
    void beforeEach() {
        createServiceIfNotExists(mockServer, "knative/quarkus-greeting.yaml", getNamespace(), getKnativeServiceName());
        createServiceIfNotExists(mockServer, "knative/quarkus-greeting-kubernetes.yaml", getNamespace(), getKubernetesServiceName());
        createServiceIfNotExists(mockServer, "knative/quarkus-greeting-openshift.yaml", getNamespace(), getOpenshiftServicename());
    }

    @Inject
    Fabric8KubernetesServiceCatalogTest(Fabric8KubernetesServiceCatalog fabric8KubernetesServiceCatalog) {
        super(fabric8KubernetesServiceCatalog);
    }
}
package org.kie.kogito.addons.springboot.k8s;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.kie.kogito.addons.k8s.Endpoint;
import org.kie.kogito.addons.k8s.EndpointDiscovery;

import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.server.mock.EnableKubernetesMockClient;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unlike Quarkus, Spring doesn't have support for an integrated Kubernetes client mock environment.
 * In this case, just to do a quick check in the internals, we manually set the mocked client into the bean factory.
 * We don't need a full check in the API since the Quarkus counterpart is already doing it.
 */
@EnableKubernetesMockClient(https = false)
public class EndpointDiscoveryTest {

    static KubernetesClient kubernetesClient;

    @Test
    void verifyKubernetesIntegration() {
        final EndpointDiscovery mockedEndpointDiscovery = new EndpointDiscoveryConfig().endpointDiscovery(kubernetesClient);
        final Optional<Endpoint> endpoint = mockedEndpointDiscovery.findEndpoint("test", "test");
        assertTrue(endpoint.isEmpty()); // we haven't created anything
    }

}

package org.kie.kogito.addons.quarkus.k8s;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Named;

import org.junit.jupiter.api.Test;
import org.kie.kogito.addons.k8s.Endpoint;
import org.kie.kogito.addons.k8s.EndpointDiscovery;

import io.fabric8.kubernetes.api.model.Service;
import io.fabric8.kubernetes.api.model.ServiceBuilder;
import io.fabric8.kubernetes.api.model.ServicePort;
import io.fabric8.kubernetes.api.model.ServiceSpec;
import io.fabric8.kubernetes.client.server.mock.KubernetesServer;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.kubernetes.client.KubernetesTestServer;
import io.quarkus.test.kubernetes.client.WithKubernetesTestServer;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

@QuarkusTest
@WithKubernetesTestServer
public class KubernetesServiceEndpointDiscoveryTest {

    @KubernetesTestServer
    KubernetesServer mockServer;

    @Named("default")
    @Inject
    EndpointDiscovery endpointDiscovery;

    private void createServiceIfNotExist(final String name, Map<String, String> labels, Integer... ports) {
        if (mockServer.getClient().services().inNamespace("test").withName(name).get() != null) {
            return;
        }
        final List<ServicePort> sPorts = new ArrayList<>();
        for (Integer integer : ports) {
            final ServicePort port = new ServicePort();
            port.setPort(integer);
            sPorts.add(port);
        }
        final Service svc = new ServiceBuilder()
                .withNewMetadata()
                .withName(name).withNamespace("test")
                .withLabels(labels)
                .and().withSpec(new ServiceSpec()).build();
        svc.getSpec().setClusterIP("127.0.0.1");
        svc.getSpec().setPorts(sPorts);

        mockServer.getClient().resource(svc).createOrReplace();
    }

    @Test
    public void testGetURLOnStandardPort() {
        createServiceIfNotExist("svc1", Collections.emptyMap(), 80, 8776);
        final Optional<Endpoint> endpoint = endpointDiscovery.findEndpoint("test", "svc1");
        assertTrue(endpoint.isPresent());
        assertFalse(endpoint.get().getUrl().isEmpty());
        try {
            new URL(endpoint.get().getUrl());
        } catch (MalformedURLException e) {
            fail("The generated URL " + endpoint.get().getUrl() + " is invalid"); //verbose
        }
    }

    @Test
    public void testGetURLOnRandomPort() {
        createServiceIfNotExist("svc2", Collections.singletonMap("app", "test1"), 8778);
        final List<Endpoint> endpoints = endpointDiscovery.findEndpoint("test", Collections.singletonMap("app", "test1"));
        assertFalse(endpoints.isEmpty());
        assertFalse(endpoints.get(0).getUrl().isEmpty());
        try {
            new URL(endpoints.get(0).getUrl());
        } catch (MalformedURLException e) {
            fail("The generated URL " + endpoints.get(0).getUrl() + " is invalid"); //verbose
        }
    }
}

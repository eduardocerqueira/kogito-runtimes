package org.kie.kogito.addons.quarkus.fabric8.k8s.service.catalog;

import java.net.URI;
import java.util.Optional;

import javax.inject.Inject;

import org.junit.jupiter.api.Test;

import io.fabric8.kubernetes.api.model.networking.v1.Ingress;
import io.fabric8.kubernetes.client.server.mock.KubernetesServer;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.kubernetes.client.KubernetesTestServer;
import io.quarkus.test.kubernetes.client.WithKubernetesTestServer;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * This test covers the queryIngressByName method from {@link IngressUtils}
 */
@QuarkusTest
@WithKubernetesTestServer
public class IngressUtilsTest {

    @KubernetesTestServer
    KubernetesServer mockServer;

    @Inject
    KubernetesResourceDiscovery discovery;

    private final String namespace = "serverless-workflow-greeting-quarkus";

    @Test
    public void testIngressNotFound() {
        Ingress ingress = mockServer.getClient()
                .network().v1().ingresses()
                .inNamespace(namespace)
                .load(this.getClass().getClassLoader().getResourceAsStream("ingress/ingress-with-ip.yaml")).get();
        mockServer.getClient().resource(ingress).inNamespace(namespace).createOrReplace();

        assertEquals(Optional.empty(),
                discovery.query(KubernetesResourceUri.parse("ingresses.v1.networking.k8s.io/" + namespace + "/invalid")));
    }

    @Test
    public void testIngressWithIP() {
        var kubeURI = KubernetesResourceUri.parse("ingresses.v1.networking.k8s.io/" + namespace + "/process-quarkus-ingress");

        Ingress ingress = mockServer.getClient().network().v1().ingresses().inNamespace(namespace)
                .load(this.getClass().getClassLoader().getResourceAsStream("ingress/ingress-with-ip.yaml")).get();

        mockServer.getClient().resource(ingress).inNamespace(namespace).createOrReplace();

        Optional<String> url = discovery.query(kubeURI).map(URI::toString);
        assertEquals("http://80.80.25.9:80", url.get());
    }

    @Test
    public void testIngressWithTLS() {
        var kubeURI = KubernetesResourceUri.parse("ingresses.v1.networking.k8s.io/" + namespace + "/hello-app-ingress-tls");

        Ingress ingress = mockServer.getClient().network().v1().ingresses().inNamespace(namespace)
                .load(this.getClass().getClassLoader().getResourceAsStream("ingress/ingress-with-tls-and-host.yaml")).get();

        mockServer.getClient().resource(ingress).inNamespace(namespace).createOrReplace();

        Optional<String> url = discovery.query(kubeURI).map(URI::toString);
        assertEquals("https://80.80.25.9:443", url.get());
    }
}

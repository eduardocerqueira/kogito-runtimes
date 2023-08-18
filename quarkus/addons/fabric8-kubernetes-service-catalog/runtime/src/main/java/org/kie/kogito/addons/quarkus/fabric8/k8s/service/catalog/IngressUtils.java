package org.kie.kogito.addons.quarkus.fabric8.k8s.service.catalog;

import java.lang.invoke.MethodHandles;
import java.net.URI;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.fabric8.kubernetes.api.model.networking.v1.Ingress;
import io.fabric8.kubernetes.client.KubernetesClient;

final class IngressUtils {

    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass().getName());

    private IngressUtils() {
    }

    /**
     * Query the given ingress exposed route
     * 
     * @param client
     * @param kubeURI
     * @return the found exposed ingress route
     */
    static Optional<URI> queryIngressByName(KubernetesClient client, KubernetesResourceUri kubeURI) {
        Ingress ingress = client
                .network()
                .v1().ingresses()
                .inNamespace(kubeURI.getNamespace())
                .withName(kubeURI.getResourceName())
                .get();

        if (ingress == null) {
            logger.error("Ingress {} not found on the {} namespace.", kubeURI.getResourceName(), kubeURI.getNamespace());
            return Optional.empty();
        }

        // for now stick with the first ip or hostname.
        String host = ingress.getStatus().getLoadBalancer().getIngress().get(0).getHostname() != null ? ingress.getStatus().getLoadBalancer().getIngress().get(0).getHostname()
                : ingress.getStatus().getLoadBalancer().getIngress().get(0).getIp();

        String scheme = !ingress.getSpec().getTls().isEmpty() ? KubeConstants.SECURE_HTTP_PROTOCOL : KubeConstants.NONSECURE_HTTP_PROTOCOL;
        Integer port = scheme.equals(KubeConstants.SECURE_HTTP_PROTOCOL) ? KubeConstants.SECURE_PORT : KubeConstants.NON_SECURE_PORT;
        // An Ingress does not expose arbitrary ports or protocols. Exposing services other than HTTP and HTTPS
        return URIUtils.builder(scheme, port, host);
    }
}

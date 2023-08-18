package org.kie.kogito.addons.k8s;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import io.fabric8.kubernetes.api.model.Service;
import io.fabric8.kubernetes.client.KubernetesClient;

public class KubernetesServiceEndpointDiscovery implements EndpointDiscovery {

    private final EndpointBuilder portBuilder = new EndpointBuilder();
    private KubernetesClient kubernetesClient;

    public KubernetesServiceEndpointDiscovery(final KubernetesClient kubernetesClient) {
        this.kubernetesClient = kubernetesClient;
    }

    @Override
    public Optional<Endpoint> findEndpoint(String namespace, String name) {
        final Service service = kubernetesClient.services().inNamespace(namespace).withName(name).get();
        if (service == null) {
            return Optional.empty();
        }
        return Optional.ofNullable(portBuilder.buildFrom(service));
    }

    @Override
    public List<Endpoint> findEndpoint(String namespace, Map<String, String> labels) {
        final List<Service> services = kubernetesClient.services().inNamespace(namespace).withLabels(labels).list().getItems();
        final List<Endpoint> endpoints = new ArrayList<>();
        services.forEach(s -> {
            final Endpoint endpoint = portBuilder.buildFrom(s);
            if (endpoint != null) {
                endpoints.add(endpoint);
            }
        });
        return endpoints;
    }
}

package org.kie.kogito.addons.k8s;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Compose instances of {@link EndpointDiscovery} to find a given endpoint among the discovery services.
 * The order the services were inserted matters. If the given endpoint is found, the service is returned immediately.
 */
public class EndpointDiscoveryComposite implements EndpointDiscovery {

    private final List<EndpointDiscovery> endpointDiscoveryList = new LinkedList<>();

    public EndpointDiscoveryComposite(EndpointDiscovery... endpointDiscovery) {
        endpointDiscoveryList.addAll(Arrays.asList(endpointDiscovery));
    }

    public void add(final EndpointDiscovery endpointDiscovery) {
        this.endpointDiscoveryList.add(endpointDiscovery);
    }

    @Override
    public Optional<Endpoint> findEndpoint(String namespace, String name) {
        for (EndpointDiscovery e : endpointDiscoveryList) {
            Optional<Endpoint> endpoint = e.findEndpoint(namespace, name);
            if (endpoint.isPresent()) {
                return endpoint;
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Endpoint> findEndpoint(String namespace, Map<String, String> labels) {
        List<Endpoint> endpoints = Collections.emptyList();
        for (EndpointDiscovery e : endpointDiscoveryList) {
            endpoints = e.findEndpoint(namespace, labels);
            if (!endpoints.isEmpty()) {
                return endpoints;
            }
        }
        return endpoints;
    }
}

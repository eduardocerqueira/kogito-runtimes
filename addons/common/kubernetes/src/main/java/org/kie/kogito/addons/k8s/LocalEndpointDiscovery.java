package org.kie.kogito.addons.k8s;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * In memory {@link EndpointDiscovery} implementation.
 * Can be used to run the service locally with a pre-loaded service registry (use {@link #addCache(EndpointQueryKey, Endpoint)}).
 * This way, the discovery service won't need to fetch the k8s API for a service, and the communication between services can
 * be tested.
 */
public class LocalEndpointDiscovery implements EndpointDiscovery {

    private Map<EndpointQueryKey, Endpoint> inMemoryCache = new HashMap<>();

    @Override
    public Optional<Endpoint> findEndpoint(String namespace, String name) {
        final EndpointQueryKey key = new EndpointQueryKey(namespace, name);
        if (inMemoryCache.containsKey(key)) {
            return Optional.of(inMemoryCache.get(key));
        }
        return Optional.empty();
    }

    @Override
    public List<Endpoint> findEndpoint(String namespace, Map<String, String> labels) {
        final EndpointQueryKey key = new EndpointQueryKey(namespace, labels);
        if (inMemoryCache.containsKey(key)) {
            return Collections.singletonList(inMemoryCache.get(key));
        }
        return Collections.emptyList();
    }

    public void addCache(final EndpointQueryKey key, final Endpoint endpoint) {
        if (key == null || endpoint == null) {
            throw new IllegalArgumentException("Nor Key or Endpoint can be null");
        }
        this.inMemoryCache.put(key, endpoint);
    }
}

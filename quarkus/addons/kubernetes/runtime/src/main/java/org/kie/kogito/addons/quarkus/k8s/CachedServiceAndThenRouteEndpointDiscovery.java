package org.kie.kogito.addons.quarkus.k8s;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.kie.kogito.addons.k8s.CacheNames;
import org.kie.kogito.addons.k8s.Endpoint;
import org.kie.kogito.addons.k8s.KnativeRouteEndpointDiscovery;
import org.kie.kogito.addons.k8s.KubernetesServiceEndpointDiscovery;
import org.kie.kogito.addons.k8s.ServiceAndThenRouteEndpointDiscovery;

import io.quarkus.cache.CacheResult;

/**
 * Cached version of the discovery service.
 * Each cache can be individually configured in the users' application.
 *
 * @see <a href="https://quarkus.io/guides/cache">Quarkus Cache Guide</a>
 */
public class CachedServiceAndThenRouteEndpointDiscovery extends ServiceAndThenRouteEndpointDiscovery {

    public CachedServiceAndThenRouteEndpointDiscovery(KubernetesServiceEndpointDiscovery kubeDiscovery, KnativeRouteEndpointDiscovery knativeDiscovery) {
        super(kubeDiscovery, knativeDiscovery);
    }

    @Override
    @CacheResult(cacheName = CacheNames.CACHE_BY_LABELS)
    public List<Endpoint> findEndpoint(String namespace, Map<String, String> labels) {
        return super.findEndpoint(namespace, labels);
    }

    @Override
    @CacheResult(cacheName = CacheNames.CACHE_BY_NAME)
    public Optional<Endpoint> findEndpoint(String namespace, String name) {
        return super.findEndpoint(namespace, name);
    }
}

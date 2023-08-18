package org.kie.kogito.addons.springboot.k8s;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.kie.kogito.addons.k8s.CacheNames;
import org.kie.kogito.addons.k8s.Endpoint;
import org.kie.kogito.addons.k8s.KnativeRouteEndpointDiscovery;
import org.kie.kogito.addons.k8s.KubernetesServiceEndpointDiscovery;
import org.kie.kogito.addons.k8s.ServiceAndThenRouteEndpointDiscovery;
import org.springframework.cache.annotation.Cacheable;

public class CacheableServiceAndThenRouteEndpointDiscovery extends ServiceAndThenRouteEndpointDiscovery {

    public CacheableServiceAndThenRouteEndpointDiscovery(KubernetesServiceEndpointDiscovery kubernetesServiceEndpointDiscovery,
            KnativeRouteEndpointDiscovery knativeRouteEndpointDiscovery) {
        super(kubernetesServiceEndpointDiscovery, knativeRouteEndpointDiscovery);
    }

    @Cacheable(cacheNames = CacheNames.CACHE_BY_NAME, cacheManager = CachingConfig.CACHE_MANAGER)
    @Override
    public Optional<Endpoint> findEndpoint(String namespace, String name) {
        return super.findEndpoint(namespace, name);
    }

    @Cacheable(cacheNames = CacheNames.CACHE_BY_LABELS, cacheManager = CachingConfig.CACHE_MANAGER)
    @Override
    public List<Endpoint> findEndpoint(String namespace, Map<String, String> labels) {
        return super.findEndpoint(namespace, labels);
    }
}

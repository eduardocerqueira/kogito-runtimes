package org.kie.kogito.addons.springboot.k8s;

import org.kie.kogito.addons.k8s.EndpointDiscovery;
import org.kie.kogito.addons.k8s.KnativeRouteEndpointDiscovery;
import org.kie.kogito.addons.k8s.KubernetesServiceEndpointDiscovery;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.fabric8.kubernetes.client.KubernetesClient;

@Configuration
public class EndpointDiscoveryConfig {

    @Bean
    public EndpointDiscovery endpointDiscovery(KubernetesClient kubernetesClient) {
        final KubernetesServiceEndpointDiscovery kubernetesServiceEndpointDiscovery = new KubernetesServiceEndpointDiscovery(kubernetesClient);
        final KnativeRouteEndpointDiscovery knativeRouteEndpointDiscovery = new KnativeRouteEndpointDiscovery(kubernetesClient);
        return new CacheableServiceAndThenRouteEndpointDiscovery(kubernetesServiceEndpointDiscovery, knativeRouteEndpointDiscovery);
    }

}

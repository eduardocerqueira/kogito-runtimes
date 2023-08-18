package org.kie.kogito.addons.quarkus.k8s;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import org.kie.kogito.addons.k8s.KnativeRouteEndpointDiscovery;
import org.kie.kogito.addons.k8s.KubernetesServiceEndpointDiscovery;
import org.kie.kogito.addons.k8s.ServiceAndThenRouteEndpointDiscovery;

import io.fabric8.kubernetes.client.KubernetesClient;

@ApplicationScoped
public class EndpointDiscoveryProducer {

    @Inject
    KubernetesClient kubernetesClient;

    @Produces
    @Singleton
    @Default
    @Named("default")
    public ServiceAndThenRouteEndpointDiscovery endpointDiscovery() {
        final KubernetesServiceEndpointDiscovery kubernetesServiceEndpointDiscovery = new KubernetesServiceEndpointDiscovery(kubernetesClient);
        final KnativeRouteEndpointDiscovery knativeRouteEndpointDiscovery = new KnativeRouteEndpointDiscovery(kubernetesClient);
        return new CachedServiceAndThenRouteEndpointDiscovery(kubernetesServiceEndpointDiscovery, knativeRouteEndpointDiscovery);
    }
}

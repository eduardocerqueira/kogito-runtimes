package org.kie.kogito.addons.k8s;

/**
 * Discovery implementation that tries to find the endpoint on a regular Kubernetes service, and then fallback to Knative if not found.
 */
public class ServiceAndThenRouteEndpointDiscovery extends EndpointDiscoveryComposite {

    public ServiceAndThenRouteEndpointDiscovery(KubernetesServiceEndpointDiscovery kubeDiscovery, KnativeRouteEndpointDiscovery knativeDiscovery) {
        super(kubeDiscovery, knativeDiscovery);
    }

}

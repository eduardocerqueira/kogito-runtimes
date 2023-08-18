package org.kie.kogito.addons.k8s;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * Entry point interface for the {@link Endpoint} discovery engine.
 */
public interface EndpointDiscovery {

    /**
     * Finds an endpoint by Kubernetes Namespace and Name
     *
     * @param namespace kubernetes namespace
     * @param name kubernetes name
     * @return an {@link Optional} endpoint
     */
    Optional<Endpoint> findEndpoint(String namespace, String name);

    /**
     * Finds an endpoint by its labels. Implementations should define the target object. For example a Service or a Knative Service.
     *
     * @param labels map containing the labels of the object.
     * @param namespace kubernetes namespace
     * @return a {@link Set} of discovered endpoints. Kubernetes objects can have the same label. The caller should know how to distinguish the endpoint.
     */
    List<Endpoint> findEndpoint(String namespace, Map<String, String> labels);
}

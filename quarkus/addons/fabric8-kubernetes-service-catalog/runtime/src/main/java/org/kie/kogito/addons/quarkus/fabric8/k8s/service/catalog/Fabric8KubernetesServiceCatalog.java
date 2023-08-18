package org.kie.kogito.addons.quarkus.fabric8.k8s.service.catalog;

import java.net.URI;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.kie.kogito.addons.k8s.resource.catalog.KubernetesServiceCatalog;
import org.kie.kogito.addons.k8s.resource.catalog.KubernetesServiceCatalogKey;

@ApplicationScoped
final class Fabric8KubernetesServiceCatalog implements KubernetesServiceCatalog {

    private final Map<KubernetesServiceCatalogKey, URI> services = new ConcurrentHashMap<>();

    private final KnativeServiceDiscovery knativeServiceDiscovery;

    private final KubernetesResourceDiscovery kubernetesResourceDiscovery;

    private final OpenShiftResourceDiscovery openShiftResourceDiscovery;

    @Inject
    Fabric8KubernetesServiceCatalog(KnativeServiceDiscovery knativeServiceDiscovery, KubernetesResourceDiscovery kubernetesResourceDiscovery,
            OpenShiftResourceDiscovery openShiftResourceDiscovery) {
        this.knativeServiceDiscovery = knativeServiceDiscovery;
        this.kubernetesResourceDiscovery = kubernetesResourceDiscovery;
        this.openShiftResourceDiscovery = openShiftResourceDiscovery;
    }

    @Override
    public Optional<URI> getServiceAddress(KubernetesServiceCatalogKey key) {
        Function<String, Optional<URI>> function;

        switch (key.getProtocol()) {
            case KNATIVE:
                String[] splitCoordinates = key.getCoordinates().split("/");

                if (splitCoordinates.length == 1) {
                    function = coordinates -> knativeServiceDiscovery.query(new KnativeServiceUri(null, coordinates));
                } else if (GVK.isValid(splitCoordinates[0])) {
                    function = coordinates -> kubernetesResourceDiscovery.query(KubernetesResourceUri.parse(coordinates));
                } else {
                    function = coordinates -> knativeServiceDiscovery.query(new KnativeServiceUri(splitCoordinates[0], splitCoordinates[1]));
                }
                break;
            case OPENSHIFT:
                function = coordinates -> openShiftResourceDiscovery.query(KubernetesResourceUri.parse(coordinates));
                break;
            case KUBERNETES:
                function = coordinates -> kubernetesResourceDiscovery.query(KubernetesResourceUri.parse(coordinates));
                break;
            default:
                throw new UnsupportedOperationException("Unsupported protocol: " + key.getProtocol());
        }

        return Optional.ofNullable(services.computeIfAbsent(key, k -> function.apply(k.getCoordinates()).orElse(null)));
    }
}

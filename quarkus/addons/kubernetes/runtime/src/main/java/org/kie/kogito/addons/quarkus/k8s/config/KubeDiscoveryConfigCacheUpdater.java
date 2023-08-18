package org.kie.kogito.addons.quarkus.k8s.config;

import java.net.URI;
import java.util.Optional;

import org.kie.kogito.addons.k8s.resource.catalog.KubernetesProtocol;
import org.kie.kogito.addons.k8s.resource.catalog.KubernetesServiceCatalog;
import org.kie.kogito.addons.k8s.resource.catalog.KubernetesServiceCatalogKey;
import org.slf4j.LoggerFactory;

class KubeDiscoveryConfigCacheUpdater {

    private final KubernetesServiceCatalog kubernetesServiceCatalog;

    KubeDiscoveryConfigCacheUpdater(KubernetesServiceCatalog kubernetesServiceCatalog) {
        this.kubernetesServiceCatalog = kubernetesServiceCatalog;
    }

    Optional<URI> update(String rawAddress) {
        return kubernetesServiceCatalog.getServiceAddress(createServiceCatalogKey(rawAddress));
    }

    private static KubernetesServiceCatalogKey createServiceCatalogKey(String rawAddress) {
        String[] protoAndValues = rawAddress.split(":");
        if (protoAndValues.length <= 1) {
            LoggerFactory.getLogger(KubeDiscoveryConfigCacheUpdater.class.getName())
                    .error("the provided URI {} is not valid", rawAddress);
        }

        KubernetesProtocol protocol = KubernetesProtocol.from(protoAndValues[0]);
        String coordinates = protoAndValues[1];

        return new KubernetesServiceCatalogKey(protocol, coordinates);
    }
}

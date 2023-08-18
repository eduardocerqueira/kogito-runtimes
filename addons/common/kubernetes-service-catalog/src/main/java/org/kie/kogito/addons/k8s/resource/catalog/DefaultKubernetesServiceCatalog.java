package org.kie.kogito.addons.k8s.resource.catalog;

import java.net.URI;
import java.util.Optional;

final class DefaultKubernetesServiceCatalog implements KubernetesServiceCatalog {

    @Override
    public Optional<URI> getServiceAddress(KubernetesServiceCatalogKey key) {
        throw new UnsupportedOperationException("You need to define a " + KubernetesServiceCatalog.class.getSimpleName() + " implementation");
    }
}

package org.kie.kogito.addons.k8s.resource.catalog;

import java.net.URI;
import java.util.Optional;

public interface KubernetesServiceCatalog {

    Optional<URI> getServiceAddress(KubernetesServiceCatalogKey key);
}

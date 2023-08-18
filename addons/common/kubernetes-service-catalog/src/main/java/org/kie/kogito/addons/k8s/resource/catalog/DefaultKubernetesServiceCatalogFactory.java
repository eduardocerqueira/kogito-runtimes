package org.kie.kogito.addons.k8s.resource.catalog;

public final class DefaultKubernetesServiceCatalogFactory {

    private DefaultKubernetesServiceCatalogFactory() {
    }

    public static KubernetesServiceCatalog createKubernetesServiceCatalog() {
        return new DefaultKubernetesServiceCatalog();
    }
}

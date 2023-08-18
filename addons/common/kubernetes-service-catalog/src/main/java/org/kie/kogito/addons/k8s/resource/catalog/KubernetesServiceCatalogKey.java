package org.kie.kogito.addons.k8s.resource.catalog;

import java.util.Objects;

public final class KubernetesServiceCatalogKey {

    private final KubernetesProtocol protocol;

    private final String coordinates;

    public KubernetesServiceCatalogKey(KubernetesProtocol protocol, String coordinates) {
        this.protocol = protocol;
        this.coordinates = coordinates;
    }

    public KubernetesProtocol getProtocol() {
        return protocol;
    }

    public String getCoordinates() {
        return coordinates;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        KubernetesServiceCatalogKey that = (KubernetesServiceCatalogKey) o;
        return protocol == that.protocol && Objects.equals(coordinates, that.coordinates);
    }

    @Override
    public int hashCode() {
        return Objects.hash(protocol, coordinates);
    }
}

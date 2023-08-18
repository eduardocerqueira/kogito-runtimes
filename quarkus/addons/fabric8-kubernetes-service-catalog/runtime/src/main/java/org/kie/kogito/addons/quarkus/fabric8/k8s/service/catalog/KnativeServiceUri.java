package org.kie.kogito.addons.quarkus.fabric8.k8s.service.catalog;

final class KnativeServiceUri {

    private final String namespace;

    private final String resourceName;

    KnativeServiceUri(String namespace, String resourceName) {
        this.namespace = namespace;
        this.resourceName = resourceName;
    }

    String getResourceName() {
        return resourceName;
    }

    String getNamespace() {
        return namespace;
    }

    static KnativeServiceUri parse(String rawUri) {
        if (rawUri.contains("/")) {
            String[] splitAddress = rawUri.split("/");
            return new KnativeServiceUri(splitAddress[0], splitAddress[1]);
        } else {
            return new KnativeServiceUri(null, rawUri);
        }
    }

    @Override
    public String toString() {
        return "KnativeFunctionUri{" +
                "namespace='" + namespace + '\'' +
                ", resourceName='" + resourceName + '\'' +
                '}';
    }
}

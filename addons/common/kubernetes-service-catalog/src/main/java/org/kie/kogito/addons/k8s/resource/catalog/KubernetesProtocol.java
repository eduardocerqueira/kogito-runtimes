package org.kie.kogito.addons.k8s.resource.catalog;

public enum KubernetesProtocol {

    KUBERNETES("kubernetes"),
    OPENSHIFT("openshift"),
    KNATIVE("knative");

    private final String value;

    KubernetesProtocol(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static KubernetesProtocol from(String value) {
        switch (value) {
            case "kubernetes":
                return KUBERNETES;
            case "openshift":
                return OPENSHIFT;
            case "knative":
                return KNATIVE;
            default:
                throw new IllegalArgumentException("The provided protocol [" + value + "] is not " +
                        "supported, supported values are 'kubernetes', 'openshift', and 'knative'");
        }
    }
}

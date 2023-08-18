package org.kie.kogito.addons.quarkus.knative.eventing.deployment;

/**
 * Kubernetes object used to deploy this Kogito service.
 */
public class KogitoServiceDeploymentTarget {

    private final String group;
    private final String version;
    private final String kind;
    private final String name;

    public KogitoServiceDeploymentTarget(String group, String version, String kind, String name) {
        this.group = group;
        this.version = version;
        this.kind = kind;
        this.name = name;
    }

    public String getGroup() {
        return group;
    }

    public String getVersion() {
        return version;
    }

    public String getKind() {
        return kind;
    }

    public String getName() {
        return name;
    }

    public String getApiVersion() {
        return String.format("%s/%s", this.group, this.version);
    }
}

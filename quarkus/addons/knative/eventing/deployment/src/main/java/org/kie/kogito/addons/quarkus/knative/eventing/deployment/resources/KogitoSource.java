package org.kie.kogito.addons.quarkus.knative.eventing.deployment.resources;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import io.fabric8.kubernetes.api.model.HasMetadata;
import io.fabric8.kubernetes.api.model.Namespaced;
import io.fabric8.kubernetes.model.annotation.Group;
import io.fabric8.kubernetes.model.annotation.Version;

/**
 * Model for the KogitoSource.
 *
 * @see <a href="https://github.com/knative-sandbox/eventing-kogito">Kogito Knative Eventing</a>
 */
// Status doesn't matter in this context, so we left it out.
// In the future, in case we need more information and share this model, this package can be exported to a module.

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "apiVersion",
        "kind",
        "metadata",
        "spec"
})
@JsonDeserialize()
@Version("v1alpha1")
@Group("kogito.knative.dev")
public class KogitoSource implements HasMetadata,
        Namespaced {

    @JsonProperty("apiVersion")
    private String apiVersion = "kogito.knative.dev/v1alpha1";
    /**
     * (Required)
     */
    @JsonProperty("kind")
    private String kind = "KogitoSource";
    @JsonProperty("metadata")
    private io.fabric8.kubernetes.api.model.ObjectMeta metadata;
    @JsonProperty("spec")
    private KogitoSourceSpec spec;

    public KogitoSource() {
    }

    public KogitoSource(String apiVersion, String kind, io.fabric8.kubernetes.api.model.ObjectMeta metadata, KogitoSourceSpec spec) {
        super();
        this.apiVersion = apiVersion;
        this.kind = kind;
        this.metadata = metadata;
        this.spec = spec;
    }

    @JsonProperty("apiVersion")
    @Override
    public String getApiVersion() {
        return apiVersion;
    }

    /**
     * (Required)
     */
    @JsonProperty("apiVersion")
    public void setApiVersion(String apiVersion) {
        this.apiVersion = apiVersion;
    }

    /**
     * (Required)
     */
    @JsonProperty("kind")
    @Override
    public String getKind() {
        return kind;
    }

    /**
     * (Required)
     */
    @JsonProperty("kind")
    public void setKind(String kind) {
        this.kind = kind;
    }

    @JsonProperty("metadata")
    public io.fabric8.kubernetes.api.model.ObjectMeta getMetadata() {
        return metadata;
    }

    @JsonProperty("metadata")
    public void setMetadata(io.fabric8.kubernetes.api.model.ObjectMeta metadata) {
        this.metadata = metadata;
    }

    @JsonProperty("spec")
    public KogitoSourceSpec getSpec() {
        return spec;
    }

    @JsonProperty("spec")
    public void setSpec(KogitoSourceSpec spec) {
        this.spec = spec;
    }
}

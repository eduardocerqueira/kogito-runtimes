package org.kie.kogito.addons.quarkus.knative.eventing.deployment.resources;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import io.fabric8.knative.internal.pkg.apis.duck.v1.CloudEventOverrides;
import io.fabric8.knative.internal.pkg.apis.duck.v1.Destination;
import io.fabric8.knative.internal.pkg.tracker.Reference;
import io.fabric8.kubernetes.api.model.KubernetesResource;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "ceOverrides",
        "sink",
        "subject"
})
@JsonDeserialize
public class KogitoSourceSpec implements KubernetesResource {

    @JsonProperty("ceOverrides")
    private CloudEventOverrides ceOverrides;
    @JsonProperty("sink")
    private Destination sink;
    @JsonProperty("subject")
    private Reference subject;

    public KogitoSourceSpec() {
    }

    public KogitoSourceSpec(CloudEventOverrides ceOverrides, Destination sink, Reference subject) {
        super();
        this.ceOverrides = ceOverrides;
        this.sink = sink;
        this.subject = subject;
    }

    @JsonProperty("ceOverrides")
    public CloudEventOverrides getCeOverrides() {
        return ceOverrides;
    }

    @JsonProperty("ceOverrides")
    public void setCeOverrides(CloudEventOverrides ceOverrides) {
        this.ceOverrides = ceOverrides;
    }

    @JsonProperty("sink")
    public Destination getSink() {
        return sink;
    }

    @JsonProperty("sink")
    public void setSink(Destination sink) {
        this.sink = sink;
    }

    @JsonProperty("subject")
    public Reference getSubject() {
        return subject;
    }

    @JsonProperty("subject")
    public void setSubject(Reference subject) {
        this.subject = subject;
    }
}

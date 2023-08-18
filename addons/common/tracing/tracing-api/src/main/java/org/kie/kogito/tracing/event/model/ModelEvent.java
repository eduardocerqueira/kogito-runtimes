package org.kie.kogito.tracing.event.model;

import org.kie.kogito.KogitoGAV;
import org.kie.kogito.ModelDomain;
import org.kie.kogito.event.ModelMetadata;
import org.kie.kogito.tracing.event.model.models.DecisionModelEvent;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Abstract <code>ModelEvent</code> to be extended by actual model-specific implementations
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "@type", visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = DecisionModelEvent.class, name = "DECISION"),
})
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class ModelEvent<T extends ModelMetadata> {

    @JsonProperty("gav")
    private KogitoGAV gav;

    @JsonProperty("name")
    private String name;

    @JsonProperty("modelMetadata")
    private T modelMetadata;

    @JsonProperty("@type")
    private ModelDomain modelDomain;

    protected ModelEvent() {

    }

    protected ModelEvent(final KogitoGAV gav,
            final String name,
            final T modelMetadata,
            final ModelDomain modelDomain) {
        this.gav = gav;
        this.name = name;
        this.modelMetadata = modelMetadata;
        this.modelDomain = modelDomain;
    }

    public KogitoGAV getGav() {
        return gav;
    }

    public String getName() {
        return name;
    }

    public T getModelMetadata() {
        return modelMetadata;
    }
}

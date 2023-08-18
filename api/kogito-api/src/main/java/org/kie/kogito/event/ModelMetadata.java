package org.kie.kogito.event;

import org.kie.kogito.ModelDomain;
import org.kie.kogito.decision.DecisionModelMetadata;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "@type", visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = DecisionModelMetadata.class, name = "DECISION"),
})
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class ModelMetadata {

    @JsonProperty("@type")
    private ModelDomain modelDomain;

    public ModelMetadata(ModelDomain modelDomain) {
        this.modelDomain = modelDomain;
    }

    public ModelDomain getModelDomain() {
        return modelDomain;
    }
}

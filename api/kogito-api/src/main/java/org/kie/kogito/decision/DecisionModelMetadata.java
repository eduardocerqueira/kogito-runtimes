package org.kie.kogito.decision;

import org.kie.kogito.ModelDomain;
import org.kie.kogito.event.ModelMetadata;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class DecisionModelMetadata extends ModelMetadata {

    @JsonProperty("specVersion")
    private String specVersion;

    public DecisionModelMetadata() {
        super(ModelDomain.DECISION);
    }

    public DecisionModelMetadata(String specVersion) {
        super(ModelDomain.DECISION);
        this.specVersion = specVersion;
    }

    public String getSpecVersion() {
        return specVersion;
    }

}

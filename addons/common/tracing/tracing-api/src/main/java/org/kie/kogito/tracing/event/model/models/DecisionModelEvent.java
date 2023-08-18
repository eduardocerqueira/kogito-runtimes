package org.kie.kogito.tracing.event.model.models;

import org.kie.kogito.KogitoGAV;
import org.kie.kogito.ModelDomain;
import org.kie.kogito.decision.DecisionModelMetadata;
import org.kie.kogito.tracing.event.model.ModelEvent;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class DecisionModelEvent extends ModelEvent<DecisionModelMetadata> {

    @JsonProperty("namespace")
    private String namespace;

    @JsonProperty("definition")
    private String definition;

    protected DecisionModelEvent() {

    }

    public DecisionModelEvent(final KogitoGAV gav,
            final String name,
            final String namespace,
            final DecisionModelMetadata decisionModelMetadata,
            final String definition) {
        super(gav, name, decisionModelMetadata, ModelDomain.DECISION);
        this.namespace = namespace;
        this.definition = definition;
    }

    public String getNamespace() {
        return namespace;
    }

    public String getDefinition() {
        return definition;
    }
}

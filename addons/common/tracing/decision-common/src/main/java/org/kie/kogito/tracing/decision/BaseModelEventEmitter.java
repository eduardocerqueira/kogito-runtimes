package org.kie.kogito.tracing.decision;

import org.kie.kogito.decision.DecisionModelResourcesProvider;
import org.kie.kogito.event.cloudevents.utils.CloudEventUtils;
import org.kie.kogito.tracing.EventEmitter;
import org.kie.kogito.tracing.event.model.ModelEvent;
import org.kie.kogito.tracing.event.model.models.DecisionModelEvent;

public abstract class BaseModelEventEmitter implements EventEmitter {

    private final DecisionModelResourcesProvider decisionModelResourcesProvider;

    public BaseModelEventEmitter(final DecisionModelResourcesProvider decisionModelResourcesProvider) {
        this.decisionModelResourcesProvider = decisionModelResourcesProvider;
    }

    protected void publishDecisionModels() {
        decisionModelResourcesProvider.get().forEach(resource -> {
            //Fire a new ModelEvent containing the model, name and namespace
            CloudEventUtils.urlEncodedURIFrom(ModelEvent.class.getName())
                    .flatMap(uri -> CloudEventUtils.build("id",
                            uri,
                            new DecisionModelEvent(
                                    resource.getGav(),
                                    resource.getModelName(),
                                    resource.getNamespace(),
                                    resource.getModelMetadata(),
                                    resource.get()),
                            ModelEvent.class))
                    .flatMap(CloudEventUtils::encode)
                    .ifPresent(this::emit);
        });
    }
}

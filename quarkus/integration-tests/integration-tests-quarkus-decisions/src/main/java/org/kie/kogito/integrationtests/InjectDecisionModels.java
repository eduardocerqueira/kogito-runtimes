package org.kie.kogito.integrationtests;

import javax.inject.Inject;

import org.kie.kogito.Application;
import org.kie.kogito.decision.DecisionConfig;
import org.kie.kogito.decision.DecisionModels;

import io.quarkus.runtime.Startup;

@Startup
public class InjectDecisionModels {

    @Inject
    public InjectDecisionModels(DecisionModels decisionModels, Application application) {
        if (decisionModels != application.get(DecisionModels.class)) {
            throw new IllegalStateException("DecisionModels should be injectable and same as instance application.get(DecisionModels.class)");
        }
        if (application.config().get(DecisionConfig.class) == null) {
            throw new IllegalStateException("DecisionConfig not available");
        }
    }
}

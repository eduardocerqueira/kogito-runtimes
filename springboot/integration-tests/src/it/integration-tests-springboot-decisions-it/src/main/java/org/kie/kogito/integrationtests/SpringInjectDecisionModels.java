package org.kie.kogito.integrationtests;

import org.springframework.beans.factory.annotation.Autowired;
import org.kie.kogito.Application;
import org.kie.kogito.decision.DecisionConfig;
import org.kie.kogito.decision.DecisionModels;

public class SpringInjectDecisionModels {

    @Autowired
    public SpringInjectDecisionModels(DecisionModels decisionModels, Application application) {
        if(decisionModels != application.get(DecisionModels.class)) {
            throw new IllegalStateException("DecisionModels should be injectable and same as instance application.get(DecisionModels.class)");
        }
        if(application.config().get(DecisionConfig.class) == null) {
            throw new IllegalStateException("DecisionConfig not available");
        }
    }
}

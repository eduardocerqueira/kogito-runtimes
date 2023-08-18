package org.kie.kogito.explainability;

import org.kie.kogito.Application;
import org.kie.kogito.explainability.model.PredictInput;
import org.kie.kogito.explainability.model.PredictOutput;

public interface ExplainabilityResourceExecutor {

    boolean acceptRequest(PredictInput predictInput);

    PredictOutput processRequest(Application application, PredictInput predictInput);
}

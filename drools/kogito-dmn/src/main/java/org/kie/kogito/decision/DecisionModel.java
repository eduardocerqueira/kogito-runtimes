package org.kie.kogito.decision;

import java.util.Map;

import org.kie.dmn.api.core.DMNContext;
import org.kie.dmn.api.core.DMNModel;
import org.kie.dmn.api.core.DMNResult;
import org.kie.dmn.api.core.FEELPropertyAccessible;

public interface DecisionModel {

    DMNContext newContext(Map<String, Object> inputSet);

    DMNContext newContext(FEELPropertyAccessible inputSet);

    DMNResult evaluateAll(DMNContext context);

    DMNResult evaluateDecisionService(DMNContext context, String decisionServiceName);

    DMNModel getDMNModel();

}

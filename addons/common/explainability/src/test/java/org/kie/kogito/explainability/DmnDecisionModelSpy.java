package org.kie.kogito.explainability;

import java.util.ArrayList;
import java.util.List;

import org.kie.dmn.api.core.DMNContext;
import org.kie.dmn.api.core.DMNResult;
import org.kie.dmn.api.core.DMNRuntime;
import org.kie.kogito.ExecutionIdSupplier;
import org.kie.kogito.dmn.DmnDecisionModel;

import static org.kie.kogito.explainability.Constants.SKIP_MONITORING;

public class DmnDecisionModelSpy extends DmnDecisionModel {

    private final List<Boolean> evaluationSkipMonitoringHistory = new ArrayList<>();

    public DmnDecisionModelSpy(DMNRuntime dmnRuntime, String namespace, String name, ExecutionIdSupplier execIdSupplier) {
        super(dmnRuntime, namespace, name, execIdSupplier);
    }

    @Override
    public DMNResult evaluateAll(DMNContext context) {
        evaluationSkipMonitoringHistory.add((boolean) context.getMetadata().asMap().getOrDefault(SKIP_MONITORING, false));
        return super.evaluateAll(context);
    }

    List<Boolean> getEvaluationSkipMonitoringHistory() {
        return evaluationSkipMonitoringHistory;
    }
}

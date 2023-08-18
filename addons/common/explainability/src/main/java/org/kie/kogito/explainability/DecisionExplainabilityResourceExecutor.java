package org.kie.kogito.explainability;

import java.util.Map;

import org.kie.dmn.api.core.DMNContext;
import org.kie.dmn.api.core.DMNResult;
import org.kie.kogito.Application;
import org.kie.kogito.decision.DecisionModel;
import org.kie.kogito.decision.DecisionModels;
import org.kie.kogito.dmn.rest.KogitoDMNResult;
import org.kie.kogito.explainability.model.ModelIdentifier;
import org.kie.kogito.explainability.model.PredictInput;
import org.kie.kogito.explainability.model.PredictOutput;

import static org.kie.kogito.explainability.Constants.SKIP_MONITORING;
import static org.kie.kogito.explainability.Constants.SKIP_TRACING;
import static org.kie.kogito.explainability.model.ModelIdentifier.RESOURCE_ID_SEPARATOR;

public class DecisionExplainabilityResourceExecutor implements ExplainabilityResourceExecutor {

    @Override
    public boolean acceptRequest(PredictInput predictInput) {
        return "dmn".equalsIgnoreCase(predictInput.getModelIdentifier().getResourceType());
    }

    @Override
    public PredictOutput processRequest(Application application, PredictInput predictInput) {
        DecisionModel decisionModel = getDecisionModel(application.get(DecisionModels.class), predictInput.getModelIdentifier());
        DMNContext dmnContext = decisionModel.newContext(convertDMNInput(predictInput));
        dmnContext.getMetadata().set(SKIP_TRACING, true);
        dmnContext.getMetadata().set(SKIP_MONITORING, true);
        return convertDMNOutput(decisionModel.evaluateAll(dmnContext), predictInput);
    }

    protected DecisionModel getDecisionModel(DecisionModels decisionModels, ModelIdentifier modelIdentifier) {
        String[] namespaceAndName = extractNamespaceAndName(modelIdentifier.getResourceId());
        return decisionModels.getDecisionModel(namespaceAndName[0], namespaceAndName[1]);
    }

    private Map<String, Object> convertDMNInput(PredictInput predictInput) {
        return predictInput.getRequest();
    }

    private PredictOutput convertDMNOutput(DMNResult dmnResult, PredictInput predictInput) {
        String[] namespaceAndName = extractNamespaceAndName(predictInput.getModelIdentifier().getResourceId());
        KogitoDMNResult result = new KogitoDMNResult(
                namespaceAndName[0],
                namespaceAndName[1],
                dmnResult);
        return new PredictOutput(predictInput.getModelIdentifier(), result.getDmnContext());
    }

    private String[] extractNamespaceAndName(String resourceId) {
        int index = resourceId.lastIndexOf(RESOURCE_ID_SEPARATOR);
        if (index < 0 || index == resourceId.length()) {
            throw new IllegalArgumentException("Malformed resourceId " + resourceId);
        }
        return new String[] { resourceId.substring(0, index), resourceId.substring(index + 1) };
    }
}

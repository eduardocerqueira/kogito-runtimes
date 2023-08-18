package org.kie.kogito.pmml;

import java.util.Map;

import org.kie.api.pmml.PMML4Result;
import org.kie.kogito.Application;

public abstract class AbstractPMMLRestResource {

    protected Object result(Application application, String fileName, String modelName, Map<String, Object> variables) {
        org.kie.kogito.prediction.PredictionModel prediction = application.get(org.kie.kogito.prediction.PredictionModels.class).getPredictionModel(fileName, modelName);
        org.kie.api.pmml.PMML4Result pmml4Result = prediction.evaluateAll(prediction.newContext(variables));
        return java.util.Collections.singletonMap(pmml4Result.getResultObjectName(), pmml4Result.getResultVariables().get(pmml4Result.getResultObjectName()));
    }

    protected PMML4Result descriptive(Application application, String fileName, String modelName, Map<String, Object> variables) {
        org.kie.kogito.prediction.PredictionModel prediction = application.get(org.kie.kogito.prediction.PredictionModels.class).getPredictionModel(fileName, modelName);
        return prediction.evaluateAll(prediction.newContext(variables));
    }

    public static String getJsonErrorMessage(Exception e) {
        String errorMessage = String.format("%1$s: %2$s", e.getClass().getName(), e.getMessage() != null ? e.getMessage() : "");
        return String.format("{\"exception\" : \"%s\"}", errorMessage);
    }
}

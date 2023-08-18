package org.kie.kogito.prediction;

import java.util.Map;

import org.kie.api.pmml.PMML4Result;
import org.kie.pmml.api.models.PMMLModel;
import org.kie.pmml.api.runtime.PMMLRuntimeContext;

public interface PredictionModel {

    PMMLRuntimeContext newContext(Map<String, Object> inputSet);

    PMML4Result evaluateAll(PMMLRuntimeContext context);

    PMMLModel getPMMLModel();

}

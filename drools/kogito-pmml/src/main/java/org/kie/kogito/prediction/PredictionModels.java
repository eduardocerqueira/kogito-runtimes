package org.kie.kogito.prediction;

import org.kie.kogito.KogitoEngine;

public interface PredictionModels extends KogitoEngine {

    PredictionModel getPredictionModel(String fileName, String modelName);

}

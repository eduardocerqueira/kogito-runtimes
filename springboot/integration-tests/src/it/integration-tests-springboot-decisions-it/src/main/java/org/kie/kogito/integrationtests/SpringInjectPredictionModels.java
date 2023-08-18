package org.kie.kogito.integrationtests;

import org.springframework.beans.factory.annotation.Autowired;
import org.kie.kogito.Application;
import org.kie.kogito.prediction.PredictionConfig;
import org.kie.kogito.prediction.PredictionModels;

public class SpringInjectPredictionModels {

    @Autowired
    public SpringInjectPredictionModels(PredictionModels predictionModels, Application application) {
        if(predictionModels != application.get(PredictionModels.class)) {
            throw new IllegalStateException("PredictionModels should be injectable and same as instance application.get(PredictionModels.class)");
        }
        if(application.config().get(PredictionConfig.class) == null) {
            throw new IllegalStateException("PredictionConfig not available");
        }
    }
}

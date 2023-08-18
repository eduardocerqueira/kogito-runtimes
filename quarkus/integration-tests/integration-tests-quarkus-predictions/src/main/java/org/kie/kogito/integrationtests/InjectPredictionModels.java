package org.kie.kogito.integrationtests;

import javax.inject.Inject;

import org.kie.kogito.Application;
import org.kie.kogito.prediction.PredictionConfig;
import org.kie.kogito.prediction.PredictionModels;

import io.quarkus.runtime.Startup;

@Startup
public class InjectPredictionModels {

    @Inject
    public InjectPredictionModels(PredictionModels predictionModels, Application application) {
        if (predictionModels != application.get(PredictionModels.class)) {
            throw new IllegalStateException("PredictionModels should be injectable and same as instance application.get(PredictionModels.class)");
        }
        if (application.config().get(PredictionConfig.class) == null) {
            throw new IllegalStateException("PredictionConfig not available");
        }
    }
}

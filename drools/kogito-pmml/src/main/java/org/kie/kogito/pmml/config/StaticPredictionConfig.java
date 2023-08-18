package org.kie.kogito.pmml.config;

import org.kie.kogito.prediction.PredictionConfig;
import org.kie.kogito.prediction.PredictionEventListenerConfig;

public class StaticPredictionConfig implements PredictionConfig {

    private final PredictionEventListenerConfig predictionEventListeners;

    public StaticPredictionConfig(PredictionEventListenerConfig predictionEventListeners) {
        this.predictionEventListeners = predictionEventListeners;
    }

    public StaticPredictionConfig() {
        this(new DefaultPredictionEventListenerConfig());
    }

    public PredictionEventListenerConfig predictionEventListeners() {
        return predictionEventListeners;
    }
}

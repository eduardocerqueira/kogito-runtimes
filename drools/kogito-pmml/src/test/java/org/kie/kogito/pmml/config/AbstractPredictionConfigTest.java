package org.kie.kogito.pmml.config;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.kie.kogito.prediction.PredictionEventListenerConfig;

import static org.assertj.core.api.Assertions.assertThat;

class AbstractPredictionConfigTest {

    @Test
    void predictionEventListenersWithoutPredictionEventListenerConfigs() {
        AbstractPredictionConfig abstractPredictionConfig = getAbstractPredictionConfig(null);
        assertThat(abstractPredictionConfig.predictionEventListeners()).isNull();
    }

    @Test
    void predictionEventListenersWithPredictionEventListenerConfigs() {
        final List<PredictionEventListenerConfig> predictionEventListenerConfigs = IntStream
                .range(0, 3)
                .mapToObj(i -> getPredictionEventListenerConfig())
                .collect(Collectors.toList());
        AbstractPredictionConfig abstractPredictionConfig = getAbstractPredictionConfig(predictionEventListenerConfigs);
        assertThat(abstractPredictionConfig.predictionEventListeners()).isEqualTo(predictionEventListenerConfigs.get(0));
    }

    private AbstractPredictionConfig getAbstractPredictionConfig(Iterable<PredictionEventListenerConfig> predictionEventListenerConfigs) {
        return new AbstractPredictionConfig(predictionEventListenerConfigs) {
            @Override
            public PredictionEventListenerConfig predictionEventListeners() {
                return super.predictionEventListeners();
            }
        };
    }

    private PredictionEventListenerConfig getPredictionEventListenerConfig() {
        return new PredictionEventListenerConfig() {

        };
    }
}
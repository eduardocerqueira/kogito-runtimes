package org.kie.kogito.eventdriven.predictions;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.kie.kogito.config.ConfigBean;
import org.kie.kogito.event.EventEmitter;
import org.kie.kogito.event.EventReceiver;
import org.kie.kogito.prediction.PredictionModels;

import io.quarkus.runtime.Startup;

@Startup
public class QuarkusEventDrivenPredictionsController extends EventDrivenPredictionsController {

    @Inject
    PredictionModels predictionModels;

    @Inject
    ConfigBean config;

    @Inject
    EventEmitter eventEmitter;

    @Inject
    EventReceiver eventReceiver;

    @PostConstruct
    private void onPostConstruct() {
        init(predictionModels, config, eventEmitter, eventReceiver);
        subscribe();
    }
}

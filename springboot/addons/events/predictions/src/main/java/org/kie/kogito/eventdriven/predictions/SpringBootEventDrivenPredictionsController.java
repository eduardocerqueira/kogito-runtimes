package org.kie.kogito.eventdriven.predictions;

import javax.annotation.PostConstruct;

import org.kie.kogito.config.ConfigBean;
import org.kie.kogito.event.EventEmitter;
import org.kie.kogito.event.EventReceiver;
import org.kie.kogito.prediction.PredictionModels;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SpringBootEventDrivenPredictionsController extends EventDrivenPredictionsController {

    @Autowired
    public SpringBootEventDrivenPredictionsController(PredictionModels predictionModels, ConfigBean config, EventEmitter eventEmitter, EventReceiver eventReceiver) {
        super(predictionModels, config, eventEmitter, eventReceiver);
    }

    @PostConstruct
    private void onPostConstruct() {
        subscribe();
    }
}

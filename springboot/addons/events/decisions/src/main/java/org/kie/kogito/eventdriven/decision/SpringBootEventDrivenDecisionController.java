package org.kie.kogito.eventdriven.decision;

import javax.annotation.PostConstruct;

import org.kie.kogito.config.ConfigBean;
import org.kie.kogito.decision.DecisionModels;
import org.kie.kogito.event.EventEmitter;
import org.kie.kogito.event.EventReceiver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SpringBootEventDrivenDecisionController extends EventDrivenDecisionController {

    @Autowired
    public SpringBootEventDrivenDecisionController(DecisionModels decisionModels, ConfigBean config, EventEmitter eventEmitter, EventReceiver eventReceiver) {
        super(decisionModels, config, eventEmitter, eventReceiver);
    }

    @PostConstruct
    private void onPostConstruct() {
        subscribe();
    }
}

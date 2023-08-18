package org.kie.kogito.eventdriven.decision;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.kie.kogito.config.ConfigBean;
import org.kie.kogito.decision.DecisionModels;
import org.kie.kogito.event.EventEmitter;
import org.kie.kogito.event.EventReceiver;

import io.quarkus.runtime.Startup;

@Startup
public class QuarkusEventDrivenDecisionController extends EventDrivenDecisionController {

    @Inject
    DecisionModels decisionModels;

    @Inject
    ConfigBean config;

    @Inject
    EventEmitter eventEmitter;

    @Inject
    EventReceiver eventReceiver;

    @PostConstruct
    private void onPostConstruct() {
        init(decisionModels, config, eventEmitter, eventReceiver);
        subscribe();
    }
}

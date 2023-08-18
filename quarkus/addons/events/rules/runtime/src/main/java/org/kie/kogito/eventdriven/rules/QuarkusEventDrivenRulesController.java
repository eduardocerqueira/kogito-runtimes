package org.kie.kogito.eventdriven.rules;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.kie.kogito.config.ConfigBean;
import org.kie.kogito.event.EventEmitter;
import org.kie.kogito.event.EventReceiver;

import io.quarkus.runtime.Startup;

@Startup
public class QuarkusEventDrivenRulesController extends EventDrivenRulesController {

    @Inject
    ConfigBean config;

    @Inject
    EventEmitter eventEmitter;

    @Inject
    EventReceiver eventReceiver;

    @PostConstruct
    private void onPostConstruct() {
        init(config, eventEmitter, eventReceiver);
    }
}

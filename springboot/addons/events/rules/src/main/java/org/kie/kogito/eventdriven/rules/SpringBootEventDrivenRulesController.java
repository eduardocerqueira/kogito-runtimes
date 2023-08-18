package org.kie.kogito.eventdriven.rules;

import org.kie.kogito.config.ConfigBean;
import org.kie.kogito.event.EventEmitter;
import org.kie.kogito.event.EventReceiver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SpringBootEventDrivenRulesController extends EventDrivenRulesController {

    @Autowired
    public SpringBootEventDrivenRulesController(ConfigBean config, EventEmitter eventEmitter, EventReceiver eventReceiver) {
        super(config, eventEmitter, eventReceiver);
    }
}

package org.kie.kogito.tracing.decision;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

/**
 * This class must always extend <code>org.kie.kogito.tracing.decision.DecisionTracingListener</code>
 * for code generation plugins to correctly detect if this addon is enabled.
 */
@Component
public final class SpringBootDecisionTracingListener extends DecisionTracingListener {

    @Autowired
    public SpringBootDecisionTracingListener(
            ApplicationEventPublisher eventPublisher,
            SpringBootDecisionTracingCollector collector,
            @Value(value = "${kogito.addon.tracing.decision.asyncEnabled:true}") boolean asyncEnabled) {
        if (asyncEnabled) {
            setEventConsumer(eventPublisher::publishEvent);
        } else {
            setEventConsumer(collector::onApplicationEvent);
        }
    }

}

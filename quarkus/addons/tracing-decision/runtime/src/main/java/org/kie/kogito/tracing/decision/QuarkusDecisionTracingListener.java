package org.kie.kogito.tracing.decision;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import io.vertx.core.eventbus.EventBus;

/**
 * This class must always extend <code>org.kie.kogito.tracing.decision.DecisionTracingListener</code>
 * for code generation plugins to correctly detect if this addon is enabled.
 */
@ApplicationScoped
public final class QuarkusDecisionTracingListener extends DecisionTracingListener {

    @Inject
    public QuarkusDecisionTracingListener(
            EventBus bus,
            QuarkusDecisionTracingCollector collector,
            @ConfigProperty(name = "kogito.addon.tracing.decision.asyncEnabled", defaultValue = "true") boolean asyncEnabled) {
        if (asyncEnabled) {
            setEventConsumer(event -> bus.send("kogito-tracing-decision_EvaluateEvent", event));
        } else {
            setEventConsumer(collector::onEvent);
        }
    }
}

package org.kie.kogito.tracing.decision;

import java.util.function.BiFunction;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.kie.kogito.Application;
import org.kie.kogito.config.ConfigBean;
import org.kie.kogito.tracing.decision.event.evaluate.EvaluateEvent;
import org.kie.kogito.tracing.decision.modelsupplier.ApplicationModelSupplier;

import io.quarkus.vertx.ConsumeEvent;

@Singleton
public class QuarkusDecisionTracingCollector {

    private final DecisionTracingCollector collector;

    public QuarkusDecisionTracingCollector(final QuarkusTraceEventEmitter eventEmitter,
            final ConfigBean configBean,
            final BiFunction<String, String, org.kie.dmn.api.core.DMNModel> modelSupplier) {
        this.collector = new DecisionTracingCollector(eventEmitter::emit, modelSupplier, configBean);
    }

    @Inject
    public QuarkusDecisionTracingCollector(final QuarkusTraceEventEmitter eventEmitter,
            final ConfigBean configBean,
            final Application application) {
        this(eventEmitter, configBean, new ApplicationModelSupplier(application));
    }

    @ConsumeEvent("kogito-tracing-decision_EvaluateEvent")
    public void onEvent(final EvaluateEvent event) {
        collector.addEvent(event);
    }
}

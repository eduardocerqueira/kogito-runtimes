package org.kie.kogito.tracing.decision;

import java.util.function.BiFunction;

import org.kie.dmn.api.core.DMNModel;
import org.kie.kogito.Application;
import org.kie.kogito.config.ConfigBean;
import org.kie.kogito.tracing.decision.event.evaluate.EvaluateEvent;
import org.kie.kogito.tracing.decision.modelsupplier.ApplicationModelSupplier;

public class SpringBootDecisionTracingCollector {

    private final DecisionTracingCollector collector;

    public SpringBootDecisionTracingCollector(final SpringBootTraceEventEmitter eventEmitter,
            final ConfigBean configBean,
            final BiFunction<String, String, DMNModel> modelSupplier) {
        this.collector = new DecisionTracingCollector(eventEmitter::emit, modelSupplier, configBean);
    }

    public SpringBootDecisionTracingCollector(final SpringBootTraceEventEmitter eventEmitter,
            final ConfigBean configBean,
            final Application application) {
        this(eventEmitter, configBean, new ApplicationModelSupplier(application));
    }

    public void onApplicationEvent(final EvaluateEvent event) {
        collector.addEvent(event);
    }
}

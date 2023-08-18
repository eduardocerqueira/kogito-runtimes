package org.kie.kogito.tracing.decision;

import java.util.function.BiFunction;

import org.kie.dmn.api.core.DMNModel;
import org.kie.kogito.Application;
import org.kie.kogito.config.ConfigBean;
import org.kie.kogito.tracing.decision.event.evaluate.EvaluateEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;

public class SpringBootDecisionTracingCollectorAsync extends SpringBootDecisionTracingCollector {

    public SpringBootDecisionTracingCollectorAsync(SpringBootTraceEventEmitter eventEmitter, ConfigBean configBean, BiFunction<String, String, DMNModel> modelSupplier) {
        super(eventEmitter, configBean, modelSupplier);
    }

    public SpringBootDecisionTracingCollectorAsync(SpringBootTraceEventEmitter eventEmitter, ConfigBean configBean, Application application) {
        super(eventEmitter, configBean, application);
    }

    @Override
    @Async("kogitoTracingDecisionAddonTaskExecutor")
    @EventListener
    public void onApplicationEvent(final EvaluateEvent event) {
        super.onApplicationEvent(event);
    }
}

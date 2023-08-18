package org.kie.kogito.tracing.decision.aggregator;

import java.util.List;
import java.util.Optional;

import org.kie.dmn.api.core.DMNModel;
import org.kie.kogito.config.ConfigBean;
import org.kie.kogito.tracing.decision.event.evaluate.EvaluateEvent;

import io.cloudevents.CloudEvent;

@FunctionalInterface
public interface Aggregator {

    Optional<CloudEvent> aggregate(DMNModel model, String executionId, List<EvaluateEvent> events, ConfigBean configBean);
}

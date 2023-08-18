package org.kie.kogito.tracing.decision.mock;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.kie.dmn.api.core.DMNModel;
import org.kie.dmn.feel.util.Pair;
import org.kie.kogito.config.ConfigBean;
import org.kie.kogito.tracing.decision.aggregator.DefaultAggregator;
import org.kie.kogito.tracing.decision.event.evaluate.EvaluateEvent;

import io.cloudevents.CloudEvent;

public class MockDefaultAggregator extends DefaultAggregator {

    final Map<String, Pair<List<EvaluateEvent>, CloudEvent>> calls = new HashMap<>();

    public Map<String, Pair<List<EvaluateEvent>, CloudEvent>> getCalls() {
        return calls;
    }

    @Override
    public Optional<CloudEvent> aggregate(DMNModel model, String executionId, List<EvaluateEvent> events, ConfigBean configBean) {
        CloudEvent result = super.aggregate(model, executionId, events, configBean).orElseThrow(IllegalStateException::new);
        calls.put(executionId, new Pair<>(events, result));
        return Optional.of(result);
    }

}

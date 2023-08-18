package org.kie.kogito.tracing.decision;

import java.io.IOException;
import java.util.List;

import org.kie.kogito.tracing.decision.event.evaluate.EvaluateEvent;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DecisionTracingTestUtils {

    public static final ObjectMapper MAPPER = new ObjectMapper();

    public static final String EVALUATE_ALL_JSON_RESOURCE = "/Traffic Violation_EvaluateEvents_evaluateAll.json";
    public static final String EVALUATE_DECISION_SERVICE_JSON_RESOURCE = "/Traffic Violation_EvaluateEvents_evaluateDecisionService.json";

    private static final TypeReference<List<EvaluateEvent>> EVALUATE_EVENT_LIST_TYPE = new TypeReference<List<EvaluateEvent>>() {
    };

    public static List<EvaluateEvent> readEvaluateEventsFromJsonResource(String resourceName) throws IOException {
        return MAPPER.readValue(DecisionTracingTestUtils.class.getResourceAsStream(resourceName),
                EVALUATE_EVENT_LIST_TYPE);
    }
}

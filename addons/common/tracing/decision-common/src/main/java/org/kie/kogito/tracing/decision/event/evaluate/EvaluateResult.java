package org.kie.kogito.tracing.decision.event.evaluate;

import java.util.List;

import org.kie.dmn.api.core.DMNResult;
import org.kie.kogito.tracing.decision.event.EventUtils;
import org.kie.kogito.tracing.event.message.Message;

import static org.kie.kogito.tracing.decision.event.EventUtils.map;

public class EvaluateResult {

    private List<EvaluateDecisionResult> decisionResults;
    private List<Message> messages;

    public EvaluateResult(List<EvaluateDecisionResult> decisionResults, List<Message> messages) {
        this.decisionResults = decisionResults;
        this.messages = messages;
    }

    private EvaluateResult() {
    }

    public List<EvaluateDecisionResult> getDecisionResults() {
        return decisionResults;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public static EvaluateResult from(DMNResult result) {
        return new EvaluateResult(
                map(result.getDecisionResults(), EvaluateDecisionResult::from),
                map(result.getMessages(), EventUtils::messageFrom));
    }
}

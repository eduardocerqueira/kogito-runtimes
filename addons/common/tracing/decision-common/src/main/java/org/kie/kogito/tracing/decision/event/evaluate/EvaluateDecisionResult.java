package org.kie.kogito.tracing.decision.event.evaluate;

import java.util.List;

import org.kie.dmn.api.core.DMNDecisionResult;
import org.kie.dmn.api.core.DMNDecisionResult.DecisionEvaluationStatus;
import org.kie.kogito.tracing.decision.event.EventUtils;
import org.kie.kogito.tracing.event.message.Message;

import static org.kie.kogito.tracing.decision.event.EventUtils.map;

public class EvaluateDecisionResult {

    private String decisionId;
    private String decisionName;
    private DecisionEvaluationStatus evaluationStatus;
    private Object result;
    private List<Message> messages;
    private boolean errors;

    public EvaluateDecisionResult(String decisionId, String decisionName, DecisionEvaluationStatus evaluationStatus, Object result, List<Message> messages, boolean errors) {
        this.decisionId = decisionId;
        this.decisionName = decisionName;
        this.evaluationStatus = evaluationStatus;
        this.result = result;
        this.messages = messages;
        this.errors = errors;
    }

    private EvaluateDecisionResult() {
    }

    public String getDecisionId() {
        return decisionId;
    }

    public String getDecisionName() {
        return decisionName;
    }

    public DecisionEvaluationStatus getEvaluationStatus() {
        return evaluationStatus;
    }

    public Object getResult() {
        return result;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public boolean hasErrors() {
        return errors;
    }

    public static EvaluateDecisionResult from(DMNDecisionResult dr) {
        return new EvaluateDecisionResult(
                dr.getDecisionId(),
                dr.getDecisionName(),
                dr.getEvaluationStatus(),
                dr.getResult(),
                map(dr.getMessages(), EventUtils::messageFrom),
                dr.hasErrors());
    }
}

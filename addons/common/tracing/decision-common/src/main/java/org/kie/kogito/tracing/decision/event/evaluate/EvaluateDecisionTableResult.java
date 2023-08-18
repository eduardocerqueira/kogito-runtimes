package org.kie.kogito.tracing.decision.event.evaluate;

import java.util.List;

import org.kie.dmn.api.core.event.AfterEvaluateDecisionTableEvent;
import org.kie.dmn.api.core.event.BeforeEvaluateDecisionTableEvent;

public class EvaluateDecisionTableResult {

    private String decisionTableName;
    private List<Integer> matches;
    private List<Integer> selected;

    private EvaluateDecisionTableResult(String decisionTableName, List<Integer> matches, List<Integer> selected) {
        this.decisionTableName = decisionTableName;
        this.matches = matches;
        this.selected = selected;
    }

    private EvaluateDecisionTableResult() {
    }

    public String getDecisionTableName() {
        return decisionTableName;
    }

    public List<Integer> getMatches() {
        return matches;
    }

    public List<Integer> getSelected() {
        return selected;
    }

    public static EvaluateDecisionTableResult from(BeforeEvaluateDecisionTableEvent event) {
        return new EvaluateDecisionTableResult(event.getDecisionTableName(), null, null);
    }

    public static EvaluateDecisionTableResult from(AfterEvaluateDecisionTableEvent event) {
        return new EvaluateDecisionTableResult(event.getDecisionTableName(), event.getMatches(), event.getSelected());
    }
}

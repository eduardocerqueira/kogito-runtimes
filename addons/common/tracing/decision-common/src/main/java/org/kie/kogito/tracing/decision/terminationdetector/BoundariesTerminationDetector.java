package org.kie.kogito.tracing.decision.terminationdetector;

import org.kie.kogito.tracing.decision.event.evaluate.EvaluateEvent;
import org.kie.kogito.tracing.decision.event.evaluate.EvaluateEventType;

public class BoundariesTerminationDetector implements TerminationDetector {

    EvaluateEvent firstEvent;
    boolean terminated;

    @Override
    public void add(EvaluateEvent event) {
        if (firstEvent == null) {
            if (isValidFirstEventType(event.getType())) {
                firstEvent = event;
            }
        } else {
            terminated = terminated || isValidLastEventType(event.getType());
        }
    }

    @Override
    public boolean isTerminated() {
        return terminated;
    }

    private boolean isValidFirstEventType(EvaluateEventType type) {
        switch (type) {
            case BEFORE_EVALUATE_ALL:
            case BEFORE_EVALUATE_DECISION_SERVICE:
                return true;

            default:
                return false;
        }
    }

    private boolean isValidLastEventType(EvaluateEventType type) {
        return firstEvent != null && (firstEvent.getType() == EvaluateEventType.BEFORE_EVALUATE_ALL && type == EvaluateEventType.AFTER_EVALUATE_ALL
                || firstEvent.getType() == EvaluateEventType.BEFORE_EVALUATE_DECISION_SERVICE && type == EvaluateEventType.AFTER_EVALUATE_DECISION_SERVICE);
    }
}

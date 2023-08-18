package org.kie.kogito.tracing.decision.terminationdetector;

import org.kie.kogito.tracing.decision.event.evaluate.EvaluateEvent;

public interface TerminationDetector {

    void add(EvaluateEvent event);

    boolean isTerminated();
}

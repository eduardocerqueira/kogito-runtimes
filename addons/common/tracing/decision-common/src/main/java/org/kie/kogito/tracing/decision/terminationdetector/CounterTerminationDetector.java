package org.kie.kogito.tracing.decision.terminationdetector;

import org.kie.kogito.tracing.decision.event.evaluate.EvaluateEvent;

public class CounterTerminationDetector implements TerminationDetector {

    int openEvents = 0;
    int totalEvents = 0;

    @Override
    public void add(EvaluateEvent event) {
        totalEvents++;
        if (event.getType().isBefore()) {
            openEvents++;
        } else {
            openEvents--;
        }
    }

    @Override
    public boolean isTerminated() {
        return totalEvents > 0 && openEvents == 0;
    }
}

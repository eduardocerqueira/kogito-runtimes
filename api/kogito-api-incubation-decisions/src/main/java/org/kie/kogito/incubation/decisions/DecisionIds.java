package org.kie.kogito.incubation.decisions;

import org.kie.kogito.incubation.common.ComponentRoot;

public class DecisionIds implements ComponentRoot {
    public LocalDecisionId get(String namespace, String name) {
        return new LocalDecisionId(namespace, name);
    }
}

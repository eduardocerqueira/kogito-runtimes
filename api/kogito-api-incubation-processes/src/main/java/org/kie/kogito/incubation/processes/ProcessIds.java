package org.kie.kogito.incubation.processes;

import org.kie.kogito.incubation.common.ComponentRoot;

public class ProcessIds implements ComponentRoot {
    public LocalProcessId get(String processId) {
        return new LocalProcessId(processId);
    }
}

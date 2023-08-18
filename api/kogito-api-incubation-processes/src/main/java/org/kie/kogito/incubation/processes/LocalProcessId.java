package org.kie.kogito.incubation.processes;

import org.kie.kogito.incubation.common.LocalId;
import org.kie.kogito.incubation.common.LocalUri;
import org.kie.kogito.incubation.common.LocalUriId;

public class LocalProcessId extends LocalUriId implements LocalId {
    public static final String PREFIX = "processes";

    private final String processId;

    public LocalProcessId(String processId) {
        super(LocalUri.Root.append(PREFIX).append(processId));
        this.processId = processId;
    }

    public String processId() {
        return processId;
    }

    public ProcessInstanceIds instances() {
        return new ProcessInstanceIds(this);
    }

    @Override
    public LocalId toLocalId() {
        return this;
    }

}

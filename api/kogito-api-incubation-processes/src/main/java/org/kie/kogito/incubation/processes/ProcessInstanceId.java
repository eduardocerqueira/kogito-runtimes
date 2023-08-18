package org.kie.kogito.incubation.processes;

import org.kie.kogito.incubation.common.LocalId;
import org.kie.kogito.incubation.common.LocalUriId;

public class ProcessInstanceId extends LocalUriId implements LocalId {

    public static final String PREFIX = "instances";

    private final LocalProcessId processId;
    private final String processInstanceId;

    public ProcessInstanceId(LocalProcessId processId, String processInstanceId) {
        super(processId.asLocalUri().append(PREFIX).append(processInstanceId));
        LocalId localDecisionId = processId.toLocalId();
        if (!localDecisionId.asLocalUri().startsWith(LocalProcessId.PREFIX)) {
            throw new IllegalArgumentException("Not a valid process path"); // fixme use typed exception
        }

        this.processId = processId;
        this.processInstanceId = processInstanceId;
    }

    @Override
    public LocalId toLocalId() {
        return this;
    }

    public LocalProcessId processId() {
        return processId;
    }

    public String processInstanceId() {
        return processInstanceId;
    }

    public TaskIds tasks() {
        return new TaskIds(this);
    }

    public SignalIds signals() {
        return new SignalIds(this);
    }

}

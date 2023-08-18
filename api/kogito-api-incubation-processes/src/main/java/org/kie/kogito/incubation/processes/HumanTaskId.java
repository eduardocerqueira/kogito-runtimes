package org.kie.kogito.incubation.processes;

import org.kie.kogito.incubation.common.LocalId;

public class HumanTaskId extends TaskId implements LocalId {

    public static final String PREFIX = "tasks";

    private final ProcessInstanceId processInstanceId;
    private final String taskId;

    public HumanTaskId(ProcessInstanceId processInstanceId, String taskId) {
        super(processInstanceId, taskId);
        LocalId localDecisionId = processInstanceId.toLocalId();
        if (!localDecisionId.asLocalUri().startsWith(LocalProcessId.PREFIX)) {
            throw new IllegalArgumentException("Not a valid process path"); // fixme use typed exception
        }

        this.processInstanceId = processInstanceId;
        this.taskId = taskId;
    }

    @Override
    public LocalId toLocalId() {
        return this;
    }

    @Override
    public ProcessInstanceId processInstanceId() {
        return processInstanceId;
    }

    @Override
    public String taskId() {
        return taskId;
    }

}

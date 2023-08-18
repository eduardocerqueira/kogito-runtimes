package org.kie.kogito.incubation.processes;

import org.kie.kogito.incubation.common.LocalId;
import org.kie.kogito.incubation.common.LocalUriId;

public class TaskId extends LocalUriId implements LocalId {

    public static final String PREFIX = "tasks";

    private final ProcessInstanceId processInstanceId;
    private final String taskId;

    public TaskId(ProcessInstanceId processInstanceId, String taskId) {
        super(processInstanceId.asLocalUri().append(PREFIX).append(taskId));
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

    public ProcessInstanceId processInstanceId() {
        return processInstanceId;
    }

    public TaskInstanceIds instances() {
        return new TaskInstanceIds(this);
    }

    public String taskId() {
        return taskId;
    }

}

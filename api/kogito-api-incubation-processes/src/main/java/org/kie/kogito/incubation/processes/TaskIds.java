package org.kie.kogito.incubation.processes;

import org.kie.kogito.incubation.common.LocalUriId;

public class TaskIds extends LocalUriId {
    private final ProcessInstanceId processInstanceId;

    public TaskIds(ProcessInstanceId processInstanceId) {
        super(processInstanceId.asLocalUri().append("tasks"));
        this.processInstanceId = processInstanceId;
    }

    public TaskId get(String taskId) {
        return new TaskId(this.processInstanceId, taskId);
    }

    public ProcessInstanceId processInstanceId() {
        return processInstanceId;
    }
}

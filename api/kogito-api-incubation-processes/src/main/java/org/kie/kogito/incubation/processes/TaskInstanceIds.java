package org.kie.kogito.incubation.processes;

public class TaskInstanceIds {
    private final TaskId taskId;

    public TaskInstanceIds(TaskId taskId) {
        this.taskId = taskId;
    }

    public TaskInstanceId get(String processInstanceId) {
        return new TaskInstanceId(taskId, processInstanceId);
    }
}

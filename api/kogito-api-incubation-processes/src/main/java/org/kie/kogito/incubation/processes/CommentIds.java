package org.kie.kogito.incubation.processes;

public class CommentIds {
    private final TaskInstanceId taskId;

    public CommentIds(TaskInstanceId taskId) {
        this.taskId = taskId;
    }

    public CommentId get(String processInstanceId) {
        return new CommentId(taskId, processInstanceId);
    }
}

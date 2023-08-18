package org.kie.kogito.incubation.processes;

public class AttachmentIds {
    private final TaskInstanceId taskInstanceId;

    public AttachmentIds(TaskInstanceId taskInstanceId) {
        this.taskInstanceId = taskInstanceId;
    }

    public AttachmentId get(String attachmentId) {
        return new AttachmentId(taskInstanceId, attachmentId);
    }
}

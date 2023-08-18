package org.kie.kogito.incubation.processes;

import org.kie.kogito.incubation.common.LocalId;
import org.kie.kogito.incubation.common.LocalUriId;

public class AttachmentId extends LocalUriId implements LocalId {

    public static final String PREFIX = "attachments";

    private final TaskInstanceId taskId;
    private final String attachmentId;

    public AttachmentId(TaskInstanceId taskId, String attachmentId) {
        super(taskId.asLocalUri().append(PREFIX).append(attachmentId));
        if (!taskId.asLocalUri().startsWith(LocalProcessId.PREFIX)) {
            throw new IllegalArgumentException("Not a valid process path"); // fixme use typed exception
        }

        this.taskId = taskId;
        this.attachmentId = attachmentId;
    }

    @Override
    public LocalId toLocalId() {
        return this;
    }

    public TaskInstanceId taskId() {
        return taskId;
    }

    public String attachmentId() {
        return attachmentId;
    }

}

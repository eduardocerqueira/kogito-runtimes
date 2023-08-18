package org.kie.kogito.incubation.processes;

import org.kie.kogito.incubation.common.LocalId;
import org.kie.kogito.incubation.common.LocalUriId;

public class TaskInstanceId extends LocalUriId implements LocalId {

    public static final String PREFIX = "instances";

    private final TaskId taskId;
    private final String taskInstanceId;

    public TaskInstanceId(TaskId taskId, String taskInstanceId) {
        super(taskId.asLocalUri().append(PREFIX).append(taskInstanceId));
        LocalId localDecisionId = taskId.toLocalId();
        if (!localDecisionId.asLocalUri().startsWith(LocalProcessId.PREFIX)) {
            throw new IllegalArgumentException("Not a valid process path"); // fixme use typed exception
        }

        this.taskId = taskId;
        this.taskInstanceId = taskInstanceId;
    }

    @Override
    public LocalId toLocalId() {
        return this;
    }

    public TaskId taskId() {
        return taskId;
    }

    public String taskInstanceId() {
        return taskInstanceId;
    }

    public AttachmentIds attachments() {
        return new AttachmentIds(this);
    }

    public CommentIds comments() {
        return new CommentIds(this);
    }
}

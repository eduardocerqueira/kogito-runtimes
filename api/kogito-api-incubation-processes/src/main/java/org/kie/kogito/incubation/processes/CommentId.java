package org.kie.kogito.incubation.processes;

import org.kie.kogito.incubation.common.LocalId;
import org.kie.kogito.incubation.common.LocalUriId;

public class CommentId extends LocalUriId implements LocalId {

    public static final String PREFIX = "comments";

    private final TaskInstanceId taskInstanceId;
    private final String commentId;

    public CommentId(TaskInstanceId taskInstanceId, String commentId) {
        super(taskInstanceId.asLocalUri().append(PREFIX).append(commentId));
        if (!taskInstanceId.asLocalUri().startsWith(LocalProcessId.PREFIX)) {
            throw new IllegalArgumentException("Not a valid process path"); // fixme use typed exception
        }

        this.taskInstanceId = taskInstanceId;
        this.commentId = commentId;
    }

    @Override
    public LocalId toLocalId() {
        return this;
    }

    public TaskInstanceId taskInstanceId() {
        return taskInstanceId;
    }

    public String commentId() {
        return commentId;
    }

}

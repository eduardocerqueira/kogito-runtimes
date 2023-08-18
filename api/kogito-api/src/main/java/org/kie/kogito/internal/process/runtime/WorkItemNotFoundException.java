package org.kie.kogito.internal.process.runtime;

public class WorkItemNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 4684154420113683086L;
    private final String workItemId;

    public WorkItemNotFoundException(String workItemId) {
        this("Cannot find work item " + workItemId, workItemId);
    }

    public WorkItemNotFoundException(String message,
            String workItemId) {
        super(message);
        this.workItemId = workItemId;
    }

    public String getWorkItemId() {
        return workItemId;
    }

}
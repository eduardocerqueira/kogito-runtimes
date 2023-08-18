package org.kie.kogito.process.workitems;

public class KogitoWorkItemHandlerNotFoundException extends RuntimeException {

    private final String workItemName;

    public KogitoWorkItemHandlerNotFoundException(String workItemName) {
        super("Could not find work item handler for " + workItemName);
        this.workItemName = workItemName;
    }

    public String getWorkItemName() {
        return workItemName;
    }

}

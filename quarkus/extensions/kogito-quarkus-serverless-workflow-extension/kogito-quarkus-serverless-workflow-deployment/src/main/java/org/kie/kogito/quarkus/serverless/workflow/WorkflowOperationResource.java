package org.kie.kogito.quarkus.serverless.workflow;

import org.kie.kogito.serverless.workflow.io.URIContentLoader;
import org.kie.kogito.serverless.workflow.operationid.WorkflowOperationId;

public class WorkflowOperationResource {

    private final WorkflowOperationId operationId;
    private final URIContentLoader contentLoader;

    public WorkflowOperationResource(WorkflowOperationId operationId, URIContentLoader contentLoader) {
        this.operationId = operationId;
        this.contentLoader = contentLoader;
    }

    public WorkflowOperationId getOperationId() {
        return operationId;
    }

    public URIContentLoader getContentLoader() {
        return contentLoader;
    }

    @Override
    public String toString() {
        return "WorkflowOperationResource [operationId=" + operationId + ", contentLoader=" + contentLoader + "]";
    }
}

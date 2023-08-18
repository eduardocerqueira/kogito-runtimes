package org.kie.kogito.serverless.workflow.operationid;

import java.net.URI;

public class WorkflowOperationId {
    private final URI uri;
    private final String operation;
    private final String fileName;
    private final String packageName;
    private final String service;

    WorkflowOperationId(URI uri, String operation, String service, String fileName, String packageName) {
        this.uri = uri;
        this.operation = operation;
        this.service = service;
        this.fileName = fileName;
        this.packageName = packageName;
    }

    public URI getUri() {
        return uri;
    }

    public String getOperation() {
        return operation;
    }

    public String getPackageName() {
        return packageName;
    }

    public String getFileName() {
        return fileName;
    }

    public String getService() {
        return service;
    }

    @Override
    public String toString() {
        return "WorkflowOperationId [uri=" + uri + ", operation=" + operation + ", fileName=" + fileName + ", packageName=" + packageName + ", service=" + service + "]";
    }
}

package org.kie.kogito.serverless.workflow.parser.handlers;

public class ActionResource {

    private final String uri;
    private final String operation;
    private final String service;

    public ActionResource(String uri, String operation, String service) {
        this.uri = uri;
        this.operation = operation;
        this.service = service;
    }

    public String getUri() {
        return uri;
    }

    public String getOperation() {
        return operation;
    }

    public String getService() {
        return service;
    }
}

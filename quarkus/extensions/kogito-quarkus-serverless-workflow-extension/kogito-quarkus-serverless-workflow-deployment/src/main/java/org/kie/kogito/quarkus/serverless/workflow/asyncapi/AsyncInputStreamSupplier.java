package org.kie.kogito.quarkus.serverless.workflow.asyncapi;

import java.io.InputStream;

import org.kie.kogito.quarkus.serverless.workflow.WorkflowOperationResource;

import io.quarkiverse.asyncapi.generator.input.InputStreamSupplier;

class AsyncInputStreamSupplier implements InputStreamSupplier {

    private final WorkflowOperationResource resource;

    public AsyncInputStreamSupplier(WorkflowOperationResource resource) {
        this.resource = resource;
    }

    @Override
    public InputStream get() {
        return resource.getContentLoader().getInputStream();
    }
}

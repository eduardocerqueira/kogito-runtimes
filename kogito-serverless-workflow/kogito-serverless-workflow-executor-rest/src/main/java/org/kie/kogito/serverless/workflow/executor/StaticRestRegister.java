package org.kie.kogito.serverless.workflow.executor;

import io.vertx.mutiny.core.Vertx;

public class StaticRestRegister implements StaticApplicationRegister {

    private final Vertx vertx;
    private final StaticRestWorkItemHandler restWorkItemHandler;

    public StaticRestRegister() {
        vertx = Vertx.vertx();
        restWorkItemHandler = new StaticRestWorkItemHandler(vertx);
    }

    @Override
    public void register(StaticWorkflowApplication application) {
        application.registerHandler(restWorkItemHandler);
    }

    @Override
    public void close() {
        restWorkItemHandler.close();
        vertx.closeAndAwait();
    }
}

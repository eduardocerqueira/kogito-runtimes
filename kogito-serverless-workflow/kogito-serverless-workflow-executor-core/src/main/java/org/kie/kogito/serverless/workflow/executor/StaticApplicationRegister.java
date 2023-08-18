package org.kie.kogito.serverless.workflow.executor;

public interface StaticApplicationRegister extends AutoCloseable {

    void register(StaticWorkflowApplication application);

    @Override
    default void close() {
    }
}

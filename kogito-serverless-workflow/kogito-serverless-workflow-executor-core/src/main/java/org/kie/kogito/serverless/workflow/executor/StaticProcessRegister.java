package org.kie.kogito.serverless.workflow.executor;

import io.serverlessworkflow.api.Workflow;

public interface StaticProcessRegister extends AutoCloseable {

    void register(StaticWorkflowApplication application, Workflow workflow, StaticWorkflowProcess process);

    @Override
    default void close() {
    }
}

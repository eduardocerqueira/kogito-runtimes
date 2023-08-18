package org.kie.kogito.serverless.workflow.executor;

import io.serverlessworkflow.api.Workflow;

public interface StaticWorkflowRegister extends AutoCloseable {

    void register(StaticWorkflowApplication application, Workflow workflow);

    @Override
    default void close() {

    }

}

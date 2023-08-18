package org.kie.kogito.quarkus.serverless.workflow.deployment.livereload;

import org.kie.kogito.quarkus.serverless.workflow.rpc.WorkflowRPCCodeGenProvider;

/**
 * Wrapper for {@link WorkflowRPCCodeGenProvider} that implements the {@link LiveReloadableCodeGenProvider} Service Provider Interface.
 */
public class LiveReloadableWorkflowRPCCodeGenProvider extends LiveReloadableCodeGenProviderBase<WorkflowRPCCodeGenProvider> {

    public LiveReloadableWorkflowRPCCodeGenProvider() {
        super(new WorkflowRPCCodeGenProvider());
    }
}

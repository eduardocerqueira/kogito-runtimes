package org.kie.kogito.serverless.workflow.executor;

import org.kie.kogito.serverless.workflow.functions.FunctionDefinitionEx;

import io.serverlessworkflow.api.Workflow;

public class StaticJavaRegister implements StaticWorkflowRegister {

    @Override
    public void register(StaticWorkflowApplication application, Workflow workflow) {
        if (workflow.getFunctions() != null && workflow.getFunctions().getFunctionDefs() != null) {
            workflow.getFunctions().getFunctionDefs().stream().filter(FunctionDefinitionEx.class::isInstance).map(FunctionDefinitionEx.class::cast)
                    .forEach(function -> application.registerHandler(new StaticFunctionWorkItemHandler<>(function.getName(), function.getFunction())));
        }
    }
}

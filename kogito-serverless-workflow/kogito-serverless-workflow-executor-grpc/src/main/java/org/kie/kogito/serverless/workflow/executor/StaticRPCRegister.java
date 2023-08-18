package org.kie.kogito.serverless.workflow.executor;

import org.kie.kogito.serverless.workflow.parser.handlers.ActionResource;
import org.kie.kogito.serverless.workflow.parser.handlers.ActionResourceFactory;
import org.kie.kogito.serverless.workflow.utils.RPCWorkflowUtils;

import io.serverlessworkflow.api.Workflow;
import io.serverlessworkflow.api.functions.FunctionDefinition;
import io.serverlessworkflow.api.functions.FunctionDefinition.Type;

public class StaticRPCRegister implements StaticWorkflowRegister {

    @Override
    public void register(StaticWorkflowApplication application, Workflow workflow) {
        if (workflow.getFunctions() != null && workflow.getFunctions().getFunctionDefs() != null) {
            workflow.getFunctions().getFunctionDefs().stream().filter(function -> function.getType() == Type.RPC).forEach(f -> registerHandler(application, f));
        }
    }

    private void registerHandler(StaticWorkflowApplication application, FunctionDefinition function) {
        ActionResource actionResource = ActionResourceFactory.getActionResource(function);
        application.registerHandler(new StaticRPCWorkItemHandler(RPCWorkflowUtils.getRPCClassName(actionResource.getService())));
    }
}

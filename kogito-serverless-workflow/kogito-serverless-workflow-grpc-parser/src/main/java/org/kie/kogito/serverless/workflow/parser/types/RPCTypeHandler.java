package org.kie.kogito.serverless.workflow.parser.types;

import java.util.Optional;

import org.jbpm.ruleflow.core.RuleFlowNodeContainerFactory;
import org.jbpm.ruleflow.core.factory.WorkItemNodeFactory;
import org.kie.kogito.serverless.workflow.operationid.WorkflowOperationId;
import org.kie.kogito.serverless.workflow.parser.ParserContext;
import org.kie.kogito.serverless.workflow.rpc.RPCWorkItemHandler;
import org.kie.kogito.serverless.workflow.utils.RPCWorkflowUtils;

import io.serverlessworkflow.api.Workflow;
import io.serverlessworkflow.api.functions.FunctionDefinition;

public class RPCTypeHandler extends WorkItemTypeHandler {

    @Override
    protected <T extends RuleFlowNodeContainerFactory<T, ?>> WorkItemNodeFactory<T> fillWorkItemHandler(Workflow workflow,
            ParserContext context,
            WorkItemNodeFactory<T> node,
            FunctionDefinition functionDef) {
        WorkflowOperationId operationId = context.operationIdFactory().from(workflow, functionDef, Optional.of(context));
        return node.workName(RPCWorkflowUtils.getRPCClassName(operationId.getService()))
                .metaData(RPCWorkItemHandler.FILE_PROP, operationId.getFileName())
                .metaData(RPCWorkItemHandler.SERVICE_PROP, operationId.getService())
                .metaData(RPCWorkItemHandler.METHOD_PROP, operationId.getOperation());
    }

    @Override
    public String type() {
        return FunctionDefinition.Type.RPC.toString();
    }

    @Override
    public boolean isCustom() {
        return false;
    }
}

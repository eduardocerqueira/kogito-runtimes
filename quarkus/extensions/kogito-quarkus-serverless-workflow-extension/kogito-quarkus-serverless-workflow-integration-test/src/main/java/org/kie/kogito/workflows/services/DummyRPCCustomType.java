package org.kie.kogito.workflows.services;

import org.jbpm.ruleflow.core.RuleFlowNodeContainerFactory;
import org.jbpm.ruleflow.core.factory.WorkItemNodeFactory;
import org.kie.kogito.serverless.workflow.parser.ParserContext;
import org.kie.kogito.serverless.workflow.parser.types.WorkItemTypeHandler;

import io.serverlessworkflow.api.Workflow;
import io.serverlessworkflow.api.functions.FunctionDefinition;

import static org.kie.kogito.serverless.workflow.parser.FunctionTypeHandlerFactory.trimCustomOperation;
import static org.kie.kogito.workflows.services.RPCCustomWorkItemHandler.NAME;
import static org.kie.kogito.workflows.services.RPCCustomWorkItemHandler.OPERATION;

public class DummyRPCCustomType extends WorkItemTypeHandler {

    @Override
    public String type() {
        return "rpc";
    }

    @Override
    protected <T extends RuleFlowNodeContainerFactory<T, ?>> WorkItemNodeFactory<T> fillWorkItemHandler(Workflow workflow,
            ParserContext context,
            WorkItemNodeFactory<T> node,
            FunctionDefinition functionDef) {
        return node.workName(NAME).metaData(OPERATION, trimCustomOperation(functionDef));
    }

}

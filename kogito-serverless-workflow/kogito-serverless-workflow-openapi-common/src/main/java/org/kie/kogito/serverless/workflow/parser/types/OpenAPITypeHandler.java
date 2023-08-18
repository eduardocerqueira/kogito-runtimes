package org.kie.kogito.serverless.workflow.parser.types;

import java.util.Optional;

import org.jbpm.ruleflow.core.RuleFlowNodeContainerFactory;
import org.jbpm.ruleflow.core.factory.WorkItemNodeFactory;
import org.kie.kogito.serverless.workflow.operationid.WorkflowOperationId;
import org.kie.kogito.serverless.workflow.parser.ParserContext;

import io.serverlessworkflow.api.Workflow;
import io.serverlessworkflow.api.functions.FunctionDefinition;

public abstract class OpenAPITypeHandler extends WorkItemTypeHandler {

    @Override
    protected <T extends RuleFlowNodeContainerFactory<T, ?>> WorkItemNodeFactory<T> fillWorkItemHandler(Workflow workflow,
            ParserContext context,
            WorkItemNodeFactory<T> node,
            FunctionDefinition functionDef) {
        WorkflowOperationId operationId = context.operationIdFactory().from(workflow, functionDef, Optional.of(context));
        return fillWorkItemHandler(workflow, context, node, functionDef, operationId);
    }

    protected abstract <T extends RuleFlowNodeContainerFactory<T, ?>> WorkItemNodeFactory<T> fillWorkItemHandler(Workflow workflow, ParserContext context,
            WorkItemNodeFactory<T> node,
            FunctionDefinition functionDef, WorkflowOperationId operationId);

    @Override
    public String type() {
        return FunctionDefinition.Type.REST.toString();
    }

    @Override
    public boolean isCustom() {
        return false;
    }
}

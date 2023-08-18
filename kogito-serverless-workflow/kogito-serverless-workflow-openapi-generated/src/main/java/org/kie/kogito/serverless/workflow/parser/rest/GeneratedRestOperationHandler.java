package org.kie.kogito.serverless.workflow.parser.rest;

import org.jbpm.ruleflow.core.RuleFlowNodeContainerFactory;
import org.jbpm.ruleflow.core.factory.WorkItemNodeFactory;
import org.kie.kogito.serverless.workflow.operationid.WorkflowOperationId;
import org.kie.kogito.serverless.workflow.parser.ParserContext;
import org.kie.kogito.serverless.workflow.parser.types.OpenAPITypeHandler;
import org.kie.kogito.serverless.workflow.utils.OpenAPIWorkflowUtils;

import io.serverlessworkflow.api.Workflow;
import io.serverlessworkflow.api.functions.FunctionDefinition;

public class GeneratedRestOperationHandler extends OpenAPITypeHandler {

    @Override
    protected <T extends RuleFlowNodeContainerFactory<T, ?>> WorkItemNodeFactory<T> fillWorkItemHandler(
            Workflow workflow, ParserContext context, WorkItemNodeFactory<T> node, FunctionDefinition functionDef,
            WorkflowOperationId operationId) {
        return node.workName(OpenAPIWorkflowUtils.getOpenApiWorkItemName(operationId.getFileName(), operationId.getOperation()));
    }
}

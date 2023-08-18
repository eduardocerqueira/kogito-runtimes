package org.kie.kogito.serverless.workflow.parser.types;

import org.jbpm.ruleflow.core.RuleFlowNodeContainerFactory;
import org.jbpm.ruleflow.core.factory.NodeFactory;
import org.jbpm.ruleflow.core.factory.WorkItemNodeFactory;
import org.kie.kogito.serverless.workflow.parser.FunctionTypeHandler;
import org.kie.kogito.serverless.workflow.parser.ParserContext;
import org.kie.kogito.serverless.workflow.parser.VariableInfo;
import org.kie.kogito.serverless.workflow.utils.WorkItemBuilder;

import io.serverlessworkflow.api.Workflow;
import io.serverlessworkflow.api.functions.FunctionDefinition;
import io.serverlessworkflow.api.functions.FunctionRef;

public abstract class WorkItemTypeHandler extends WorkItemBuilder implements FunctionTypeHandler {
    @Override
    public NodeFactory<?, ?> getActionNode(Workflow workflow, ParserContext context, RuleFlowNodeContainerFactory<?, ?> embeddedSubProcess, FunctionDefinition functionDef, FunctionRef functionRef,
            VariableInfo varInfo) {
        validateArgs(functionRef);
        return addFunctionArgs(workflow,
                fillWorkItemHandler(workflow, context, buildWorkItem(embeddedSubProcess, context, varInfo.getInputVar(), varInfo.getOutputVar()).name(functionDef.getName()), functionDef),
                functionRef);
    }

    protected abstract <T extends RuleFlowNodeContainerFactory<T, ?>> WorkItemNodeFactory<T> fillWorkItemHandler(Workflow workflow, ParserContext context, WorkItemNodeFactory<T> node,
            FunctionDefinition functionDef);

}

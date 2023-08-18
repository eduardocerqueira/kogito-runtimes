package org.kie.kogito.serverless.workflow.functions;

import org.jbpm.ruleflow.core.RuleFlowNodeContainerFactory;
import org.jbpm.ruleflow.core.factory.NodeFactory;
import org.jbpm.ruleflow.core.factory.WorkItemNodeFactory;
import org.kie.kogito.serverless.workflow.parser.FunctionNamespace;
import org.kie.kogito.serverless.workflow.parser.ParserContext;
import org.kie.kogito.serverless.workflow.parser.VariableInfo;
import org.kie.kogito.serverless.workflow.utils.WorkItemBuilder;

import io.serverlessworkflow.api.Workflow;
import io.serverlessworkflow.api.functions.FunctionRef;

public abstract class WorkItemFunctionNamespace extends WorkItemBuilder implements FunctionNamespace {

    @Override
    public NodeFactory<?, ?> getActionNode(Workflow workflow,
            ParserContext context,
            RuleFlowNodeContainerFactory<?, ?> embeddedSubProcess,
            FunctionRef functionRef,
            VariableInfo varInfo) {
        validateArgs(functionRef);
        return addFunctionArgs(workflow,
                fillWorkItemHandler(workflow, context, buildWorkItem(embeddedSubProcess, context, varInfo.getInputVar(), varInfo.getOutputVar()), functionRef).name(functionRef.getRefName()),
                functionRef);
    }

    protected abstract <T extends RuleFlowNodeContainerFactory<T, ?>> WorkItemNodeFactory<T> fillWorkItemHandler(Workflow workflow, ParserContext context, WorkItemNodeFactory<T> node,
            FunctionRef functionRef);
}

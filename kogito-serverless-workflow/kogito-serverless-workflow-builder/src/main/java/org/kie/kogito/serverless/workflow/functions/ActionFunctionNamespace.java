package org.kie.kogito.serverless.workflow.functions;

import org.jbpm.ruleflow.core.RuleFlowNodeContainerFactory;
import org.jbpm.ruleflow.core.factory.ActionNodeFactory;
import org.jbpm.ruleflow.core.factory.NodeFactory;
import org.kie.kogito.serverless.workflow.parser.FunctionNamespace;
import org.kie.kogito.serverless.workflow.parser.ParserContext;
import org.kie.kogito.serverless.workflow.parser.VariableInfo;

import io.serverlessworkflow.api.Workflow;
import io.serverlessworkflow.api.functions.FunctionRef;

public abstract class ActionFunctionNamespace implements FunctionNamespace {

    @Override
    public NodeFactory<?, ?> getActionNode(Workflow workflow,
            ParserContext context,
            RuleFlowNodeContainerFactory<?, ?> embeddedSubProcess,
            FunctionRef functionRef,
            VariableInfo varInfo) {
        return fillAction(workflow, embeddedSubProcess
                .actionNode(context.newId())
                .name(functionRef.getRefName()), functionRef, varInfo);
    }

    protected abstract <T extends RuleFlowNodeContainerFactory<T, ?>> ActionNodeFactory<T> fillAction(Workflow workflow, ActionNodeFactory<T> node, FunctionRef functionRef, VariableInfo varInfo);
}

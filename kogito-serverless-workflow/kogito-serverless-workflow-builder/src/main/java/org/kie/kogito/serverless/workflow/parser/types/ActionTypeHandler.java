package org.kie.kogito.serverless.workflow.parser.types;

import org.jbpm.ruleflow.core.RuleFlowNodeContainerFactory;
import org.jbpm.ruleflow.core.factory.ActionNodeFactory;
import org.jbpm.ruleflow.core.factory.NodeFactory;
import org.kie.kogito.serverless.workflow.parser.FunctionTypeHandler;
import org.kie.kogito.serverless.workflow.parser.ParserContext;
import org.kie.kogito.serverless.workflow.parser.VariableInfo;

import io.serverlessworkflow.api.Workflow;
import io.serverlessworkflow.api.functions.FunctionDefinition;
import io.serverlessworkflow.api.functions.FunctionRef;

import static org.kie.kogito.serverless.workflow.parser.handlers.ActionNodeUtils.actionNode;

public abstract class ActionTypeHandler implements FunctionTypeHandler {

    @Override
    public NodeFactory<?, ?> getActionNode(Workflow workflow,
            ParserContext context,
            RuleFlowNodeContainerFactory<?, ?> embeddedSubProcess,
            FunctionDefinition functionDef,
            FunctionRef functionRef,
            VariableInfo varInfo) {
        return fillAction(workflow, actionNode(embeddedSubProcess, context, functionDef), functionDef, functionRef, varInfo);
    }

    protected abstract <T extends RuleFlowNodeContainerFactory<T, ?>> ActionNodeFactory<T> fillAction(Workflow workflow, ActionNodeFactory<T> node, FunctionDefinition functionDef,
            FunctionRef functionRef, VariableInfo varInfo);

}

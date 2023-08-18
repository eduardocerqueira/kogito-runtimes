package org.kie.kogito.serverless.workflow.parser;

import org.jbpm.ruleflow.core.RuleFlowNodeContainerFactory;
import org.jbpm.ruleflow.core.factory.NodeFactory;

import io.serverlessworkflow.api.Workflow;
import io.serverlessworkflow.api.functions.FunctionDefinition;
import io.serverlessworkflow.api.functions.FunctionRef;

public interface FunctionTypeHandler {

    NodeFactory<?, ?> getActionNode(Workflow workflow, ParserContext context, RuleFlowNodeContainerFactory<?, ?> embeddedSubProcess, FunctionDefinition functionDef, FunctionRef functionRef,
            VariableInfo varInfo);

    String type();

    default boolean isCustom() {
        return true;
    }
}

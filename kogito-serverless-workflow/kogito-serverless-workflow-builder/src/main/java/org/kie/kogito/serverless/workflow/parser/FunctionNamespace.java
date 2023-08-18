package org.kie.kogito.serverless.workflow.parser;

import org.jbpm.ruleflow.core.RuleFlowNodeContainerFactory;
import org.jbpm.ruleflow.core.factory.NodeFactory;

import io.serverlessworkflow.api.Workflow;
import io.serverlessworkflow.api.functions.FunctionRef;

public interface FunctionNamespace {

    NodeFactory<?, ?> getActionNode(Workflow workflow, ParserContext context, RuleFlowNodeContainerFactory<?, ?> embeddedSubProcess, FunctionRef functionRef,
            VariableInfo varInfo);

    String namespace();
}

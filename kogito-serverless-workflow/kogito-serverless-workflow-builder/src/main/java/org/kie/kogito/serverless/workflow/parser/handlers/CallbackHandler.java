package org.kie.kogito.serverless.workflow.parser.handlers;

import org.jbpm.ruleflow.core.RuleFlowNodeContainerFactory;
import org.jbpm.ruleflow.core.factory.CompositeContextNodeFactory;
import org.jbpm.ruleflow.core.factory.NodeFactory;
import org.kie.kogito.serverless.workflow.parser.ParserContext;

import io.serverlessworkflow.api.Workflow;
import io.serverlessworkflow.api.states.CallbackState;

public class CallbackHandler extends CompositeContextNodeHandler<CallbackState> {

    protected CallbackHandler(CallbackState state, Workflow workflow, ParserContext parserContext) {
        super(state, workflow, parserContext);
    }

    @Override
    public boolean usedForCompensation() {
        return state.isUsedForCompensation();
    }

    @Override
    public MakeNodeResult makeNode(RuleFlowNodeContainerFactory<?, ?> factory) {
        CompositeContextNodeFactory<?> embeddedSubProcess = factory.compositeContextNode(parserContext.newId()).name(state.getName()).autoComplete(true);
        NodeFactory<?, ?> currentNode = embeddedSubProcess.startNode(parserContext.newId()).name("EmbeddedStart");
        if (state.getAction() != null) {
            currentNode = connect(currentNode, getActionNode(embeddedSubProcess, state.getAction()));
        }
        currentNode = connect(currentNode, makeTimeoutNode(embeddedSubProcess,
                filterAndMergeNode(embeddedSubProcess, state.getEventDataFilter(), (f, inputVar, outputVar) -> consumeEventNode(f, state.getEventRef(), inputVar, outputVar))));
        connect(currentNode, embeddedSubProcess.endNode(parserContext.newId()).name("EmbeddedEnd").terminate(true)).done();
        handleErrors(factory, embeddedSubProcess);
        return new MakeNodeResult(embeddedSubProcess);
    }
}

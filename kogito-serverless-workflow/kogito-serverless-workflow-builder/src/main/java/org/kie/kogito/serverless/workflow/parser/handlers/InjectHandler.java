package org.kie.kogito.serverless.workflow.parser.handlers;

import org.jbpm.ruleflow.core.RuleFlowNodeContainerFactory;
import org.jbpm.ruleflow.core.factory.ActionNodeFactory;
import org.kie.kogito.serverless.workflow.parser.ParserContext;
import org.kie.kogito.serverless.workflow.suppliers.InjectActionSupplier;

import com.fasterxml.jackson.databind.JsonNode;

import io.serverlessworkflow.api.Workflow;
import io.serverlessworkflow.api.states.InjectState;

public class InjectHandler extends StateHandler<InjectState> {

    protected InjectHandler(InjectState state, Workflow workflow, ParserContext parserContext) {
        super(state, workflow, parserContext);
    }

    @Override
    public boolean usedForCompensation() {
        return state.isUsedForCompensation();
    }

    @Override
    public MakeNodeResult makeNode(RuleFlowNodeContainerFactory<?, ?> factory) {
        ActionNodeFactory<?> actionNodeFactory = factory.actionNode(parserContext.newId()).name(state.getName());
        JsonNode node = state.getData();
        if (node != null) {
            actionNodeFactory.action(new InjectActionSupplier(node));
        }
        return new MakeNodeResult(actionNodeFactory);
    }

}

package org.kie.kogito.serverless.workflow.parser.handlers;

import org.jbpm.ruleflow.core.RuleFlowNodeContainerFactory;
import org.kie.kogito.serverless.workflow.parser.ParserContext;

import io.serverlessworkflow.api.Workflow;
import io.serverlessworkflow.api.states.SleepState;

public class SleepHandler extends StateHandler<SleepState> {

    protected SleepHandler(SleepState state, Workflow workflow, ParserContext parserContext) {
        super(state, workflow, parserContext);
    }

    @Override
    public boolean usedForCompensation() {
        return state.isUsedForCompensation();
    }

    @Override
    protected MakeNodeResult makeNode(RuleFlowNodeContainerFactory<?, ?> factory) {
        return new MakeNodeResult(factory.timerNode(parserContext.newId()).name(state.getName()).delay(state.getDuration()));
    }

}

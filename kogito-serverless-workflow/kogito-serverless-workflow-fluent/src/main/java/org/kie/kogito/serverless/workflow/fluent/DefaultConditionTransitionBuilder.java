package org.kie.kogito.serverless.workflow.fluent;

import io.serverlessworkflow.api.defaultdef.DefaultConditionDefinition;
import io.serverlessworkflow.api.end.End;
import io.serverlessworkflow.api.states.DefaultState;
import io.serverlessworkflow.api.transitions.Transition;

public class DefaultConditionTransitionBuilder<T> extends TransitionBuilder<T> {

    private DefaultConditionDefinition condition;

    protected DefaultConditionTransitionBuilder(T container, WorkflowBuilder workflow, DefaultState lastState, DefaultConditionDefinition condition) {
        super(container, workflow, lastState);
        this.condition = condition;
    }

    @Override
    protected void addTransition(DefaultState state) {
        if (condition != null) {
            condition.setTransition(new Transition(state.getName()));
            condition = null;
        } else {
            super.addTransition(state);
        }
    }

    @Override
    protected void addEnd(End end) {
        if (condition != null) {
            condition.setEnd(end);
            condition = null;
        } else {
            super.addEnd(end);
        }
    }
}

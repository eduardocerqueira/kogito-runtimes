package org.kie.kogito.serverless.workflow.fluent;

import java.util.ArrayList;
import java.util.List;

import io.serverlessworkflow.api.actions.Action;
import io.serverlessworkflow.api.states.DefaultState.Type;
import io.serverlessworkflow.api.states.ForEachState;

public class ForEachStateBuilder extends StateBuilder<ForEachStateBuilder, ForEachState> {

    private List<Action> actions = new ArrayList<>();

    protected ForEachStateBuilder(String inputExpr) {
        super(new ForEachState().withType(Type.FOREACH).withInputCollection(inputExpr));
        state.withActions(actions);
    }

    public ForEachStateBuilder loopVar(String loopVar) {
        state.withIterationParam(loopVar);
        return this;
    }

    public ForEachStateBuilder outputCollection(String outputExpr) {
        state.withOutputCollection(outputExpr);
        return this;
    }

    public ForEachStateBuilder action(ActionBuilder builder) {
        builder.getFunction().ifPresent(functionDefinitions::add);
        builder.getEvent().ifPresent(eventDefinitions::add);
        actions.add(builder.build());
        return this;
    }
}

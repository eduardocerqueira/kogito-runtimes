package org.kie.kogito.serverless.workflow.fluent;

import java.util.ArrayList;
import java.util.List;

import io.serverlessworkflow.api.actions.Action;
import io.serverlessworkflow.api.states.DefaultState.Type;
import io.serverlessworkflow.api.states.OperationState;

public class OperationStateBuilder extends StateBuilder<OperationStateBuilder, OperationState> {

    private List<Action> actions = new ArrayList<>();

    protected OperationStateBuilder() {
        super(new OperationState().withType(Type.OPERATION));
        state.withActions(actions);
    }

    public OperationStateBuilder action(ActionBuilder builder) {
        builder.getFunction().ifPresent(functionDefinitions::add);
        builder.getEvent().ifPresent(eventDefinitions::add);
        actions.add(builder.build());
        return this;
    }
}

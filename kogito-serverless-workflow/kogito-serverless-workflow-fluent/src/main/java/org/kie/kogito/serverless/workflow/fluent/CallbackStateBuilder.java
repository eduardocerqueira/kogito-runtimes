package org.kie.kogito.serverless.workflow.fluent;

import io.serverlessworkflow.api.states.CallbackState;
import io.serverlessworkflow.api.states.DefaultState.Type;

public class CallbackStateBuilder extends StateBuilder<CallbackStateBuilder, CallbackState> {

    protected CallbackStateBuilder(EventDefBuilder eventBuilder, ActionBuilder actionBuilder) {
        super(new CallbackState().withType(Type.CALLBACK).withAction(actionBuilder.build()).withEventRef(eventBuilder.getName()));
        actionBuilder.getEvent().ifPresent(eventDefinitions::add);
        actionBuilder.getFunction().ifPresent(functionDefinitions::add);
        eventDefinitions.add(eventBuilder);
    }

}

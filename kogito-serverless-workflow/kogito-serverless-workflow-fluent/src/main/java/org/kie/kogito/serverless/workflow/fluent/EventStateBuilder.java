package org.kie.kogito.serverless.workflow.fluent;

import java.util.ArrayList;
import java.util.List;

import io.serverlessworkflow.api.events.OnEvents;
import io.serverlessworkflow.api.states.DefaultState.Type;
import io.serverlessworkflow.api.states.EventState;

public class EventStateBuilder extends StateBuilder<EventStateBuilder, EventState> {

    private List<OnEvents> onEvents = new ArrayList<>();

    protected EventStateBuilder() {
        super(new EventState().withType(Type.EVENT));
        state.withOnEvents(onEvents);
    }

    public EventStateBuilder exclusive(boolean exclusive) {
        state.withExclusive(exclusive);
        return this;
    }

    public EventBranchBuilder events() {
        OnEvents events = new OnEvents();
        onEvents.add(events);
        return new EventBranchBuilder(this, events);
    }
}

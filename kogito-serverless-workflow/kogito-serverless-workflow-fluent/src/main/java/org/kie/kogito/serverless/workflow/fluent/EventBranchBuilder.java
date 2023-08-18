package org.kie.kogito.serverless.workflow.fluent;

import java.util.ArrayList;
import java.util.List;

import io.serverlessworkflow.api.actions.Action;
import io.serverlessworkflow.api.events.OnEvents;
import io.serverlessworkflow.api.filters.EventDataFilter;

public class EventBranchBuilder {

    private EventStateBuilder parent;
    private List<Action> actions = new ArrayList<>();
    private List<String> eventsRef = new ArrayList<>();
    private OnEvents onEvents;

    public EventBranchBuilder(EventStateBuilder parent, OnEvents onEvents) {
        this.parent = parent;
        this.onEvents = onEvents.withActions(actions).withEventRefs(eventsRef);
    }

    public EventBranchBuilder action(ActionBuilder action) {
        action.getFunction().ifPresent(parent.getFunctions()::add);
        action.getEvent().ifPresent(parent.getEvents()::add);
        actions.add(action.build());
        return this;
    }

    private EventDataFilter getFilter() {
        EventDataFilter eventFilter = onEvents.getEventDataFilter();
        if (eventFilter == null) {
            eventFilter = new EventDataFilter();
            onEvents.withEventDataFilter(eventFilter);
        }

        return eventFilter;
    }

    public EventBranchBuilder data(String expr) {
        getFilter().withData(expr);
        return this;
    }

    public EventBranchBuilder outputFilter(String expr) {
        getFilter().withToStateData(expr);
        return this;
    }

    public EventBranchBuilder event(EventDefBuilder event) {
        parent.getEvents().add(event);
        eventsRef.add(event.getName());
        return this;
    }

    public EventStateBuilder endBranch() {
        return parent;
    }
}

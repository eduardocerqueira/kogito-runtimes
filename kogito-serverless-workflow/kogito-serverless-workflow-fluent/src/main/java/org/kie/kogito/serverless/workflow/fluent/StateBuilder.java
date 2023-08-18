package org.kie.kogito.serverless.workflow.fluent;

import java.time.Duration;
import java.util.Collection;
import java.util.HashSet;

import com.fasterxml.jackson.databind.JsonNode;

import io.serverlessworkflow.api.filters.StateDataFilter;
import io.serverlessworkflow.api.states.DefaultState;
import io.serverlessworkflow.api.timeouts.StateExecTimeout;
import io.serverlessworkflow.api.timeouts.TimeoutsDefinition;

public abstract class StateBuilder<T extends StateBuilder<T, S>, S extends DefaultState> {

    public static InjectStateBuilder inject(JsonNode data) {
        return new InjectStateBuilder(data);
    }

    public static OperationStateBuilder operation() {
        return new OperationStateBuilder();
    }

    public static ParallelStateBuilder parallel() {
        return new ParallelStateBuilder();
    }

    public static CallbackStateBuilder callback(ActionBuilder action, EventDefBuilder event) {
        return new CallbackStateBuilder(event, action);
    }

    public static EventStateBuilder event() {
        return new EventStateBuilder();
    }

    public static ForEachStateBuilder forEach(String inputExpr) {
        return new ForEachStateBuilder(inputExpr);
    }

    protected final S state;
    protected final Collection<FunctionBuilder> functionDefinitions = new HashSet<>();
    protected final Collection<EventDefBuilder> eventDefinitions = new HashSet<>();

    Collection<FunctionBuilder> getFunctions() {
        return functionDefinitions;
    }

    Collection<EventDefBuilder> getEvents() {
        return eventDefinitions;
    }

    protected StateBuilder(S state) {
        this.state = state;
    }

    public T name(String name) {
        state.withName(name);
        return (T) this;
    }

    public T stateTimeout(Duration duration) {
        timeouts().withStateExecTimeout(new StateExecTimeout().withSingle(duration.toString()));
        return (T) this;
    }

    public T eventTimeout(Duration duration) {
        timeouts().withEventTimeout(duration.toString());
        return (T) this;
    }

    private TimeoutsDefinition timeouts() {
        TimeoutsDefinition timeouts = state.getTimeouts();
        if (timeouts == null) {
            timeouts = new TimeoutsDefinition();
            state.withTimeouts(timeouts);
        }
        return timeouts;
    }

    private StateDataFilter getFilter() {
        StateDataFilter filter = state.getStateDataFilter();
        if (filter == null) {
            filter = new StateDataFilter();
            state.withStateDataFilter(filter);
        }
        return filter;
    }

    public T inputFilter(String filter) {
        getFilter().withInput(filter);
        return (T) this;
    }

    public T outputFilter(String filter) {
        getFilter().withOutput(filter);
        return (T) this;
    }

    public S build() {
        return ensureName(state);
    }

    private static int counter;

    protected static <T extends DefaultState> T ensureName(T state) {
        if (state.getName() == null) {
            state.setName(state.getType() + "_" + counter++);
        }
        return state;
    }
}

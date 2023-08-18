package org.jbpm.workflow.core.node;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import org.jbpm.process.core.event.EventTypeFilter;
import org.jbpm.process.core.timer.Timer;
import org.jbpm.workflow.core.DroolsAction;
import org.kie.api.definition.process.Node;

public class EventSubProcessNode extends CompositeContextNode {

    private static final long serialVersionUID = 2200928773922042238L;

    private List<String> events = new ArrayList<>();
    private List<EventTypeFilter> eventTypeFilters = new ArrayList<>();
    private boolean keepActive = true;

    public void addEvent(EventTypeFilter filter) {
        String type = filter.getType();
        this.events.add(type);
        this.eventTypeFilters.add(filter);
    }

    public List<String> getEvents() {
        return events;
    }

    public boolean isKeepActive() {
        return keepActive;
    }

    public void setKeepActive(boolean triggerOnActivation) {
        this.keepActive = triggerOnActivation;
    }

    public StartNode findStartNode() {
        for (Node node : getNodes()) {
            if (node instanceof StartNode) {
                return (StartNode) node;
            }
        }
        return null;
    }

    @Override
    public void addTimer(Timer timer, DroolsAction action) {
        super.addTimer(timer, action);
        if (timer.getTimeType() == Timer.TIME_CYCLE) {
            setKeepActive(false);
        }
    }

    @Override
    public boolean acceptsEvent(String type, Object event, Function<String, Object> resolver) {
        for (EventTypeFilter filter : this.eventTypeFilters) {
            if (filter.acceptsEvent(type, event, resolver)) {
                return true;
            }
        }
        return super.acceptsEvent(type, event, resolver);
    }

    @Override
    public String getVariableName() {
        StartNode startNode = findStartNode();
        if (startNode != null) {
            return (String) startNode.getMetaData("TriggerMapping");
        }

        return super.getVariableName();
    }

}

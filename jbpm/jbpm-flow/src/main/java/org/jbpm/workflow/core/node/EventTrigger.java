package org.jbpm.workflow.core.node;

import java.util.ArrayList;
import java.util.List;

import org.jbpm.process.core.event.EventFilter;

public class EventTrigger extends Trigger {

    private static final long serialVersionUID = 510l;

    private List<EventFilter> filters = new ArrayList<>();

    public void addEventFilter(EventFilter eventFilter) {
        filters.add(eventFilter);
    }

    public void removeEventFilter(EventFilter eventFilter) {
        filters.remove(eventFilter);
    }

    public List<EventFilter> getEventFilters() {
        return filters;
    }

    public void setEventFilters(List<EventFilter> filters) {
        this.filters = filters;
    }

}

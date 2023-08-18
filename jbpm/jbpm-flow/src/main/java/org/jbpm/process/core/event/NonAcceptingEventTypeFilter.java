package org.jbpm.process.core.event;

import java.util.function.Function;

public class NonAcceptingEventTypeFilter extends EventTypeFilter {

    private static final long serialVersionUID = 510l;

    /**
     * Nodes that use this event filter should never be triggered by this event
     */
    @Override
    public boolean acceptsEvent(String type, Object event, Function<String, Object> resolver) {
        return false;
    }

}

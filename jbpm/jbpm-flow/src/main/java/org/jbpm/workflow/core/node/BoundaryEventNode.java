package org.jbpm.workflow.core.node;

import java.util.function.Function;

import org.jbpm.process.core.event.EventFilter;

public class BoundaryEventNode extends EventNode {

    private static final long serialVersionUID = 3448981074702415561L;

    private String attachedToNodeId;

    public String getAttachedToNodeId() {
        return attachedToNodeId;
    }

    public void setAttachedToNodeId(String attachedToNodeId) {
        this.attachedToNodeId = attachedToNodeId;
    }

    @Override
    public boolean acceptsEvent(String type, Object event, Function<String, Object> resolver) {
        for (EventFilter filter : getEventFilters()) {
            if (filter.acceptsEvent(type, event, resolver)) {
                return true;
            }
        }
        return super.acceptsEvent(type, event, resolver);
    }
}

package org.jbpm.ruleflow.core.factory;

import org.jbpm.process.core.event.EventTypeFilter;
import org.jbpm.ruleflow.core.RuleFlowNodeContainerFactory;
import org.jbpm.workflow.core.NodeContainer;
import org.jbpm.workflow.core.node.EventSubProcessNode;

public class EventSubProcessNodeFactory<T extends RuleFlowNodeContainerFactory<T, ?>> extends AbstractCompositeNodeFactory<EventSubProcessNodeFactory<T>, T> {

    public static final String METHOD_KEEP_ACTIVE = "keepActive";
    public static final String METHOD_EVENT = "event";

    public EventSubProcessNodeFactory(T nodeContainerFactory, NodeContainer nodeContainer, long id) {
        super(nodeContainerFactory, nodeContainer, new EventSubProcessNode(), id);
    }

    public EventSubProcessNodeFactory<T> keepActive(boolean keepActive) {
        ((EventSubProcessNode) getCompositeNode()).setKeepActive(keepActive);
        return this;
    }

    public EventSubProcessNodeFactory<T> event(String event) {
        EventTypeFilter filter = new EventTypeFilter();
        filter.setType(event);
        ((EventSubProcessNode) getCompositeNode()).addEvent(filter);
        return this;
    }
}

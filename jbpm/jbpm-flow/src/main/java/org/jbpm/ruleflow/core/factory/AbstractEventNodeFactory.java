package org.jbpm.ruleflow.core.factory;

import org.jbpm.process.core.event.EventFilter;
import org.jbpm.process.core.event.EventTypeFilter;
import org.jbpm.ruleflow.core.RuleFlowNodeContainerFactory;
import org.jbpm.workflow.core.Node;
import org.jbpm.workflow.core.NodeContainer;
import org.jbpm.workflow.core.node.EventNode;

public abstract class AbstractEventNodeFactory<T extends AbstractEventNodeFactory<T, P>, P extends RuleFlowNodeContainerFactory<P, ?>> extends ExtendedNodeFactory<T, P> {

    protected AbstractEventNodeFactory(P nodeContainerFactory, NodeContainer nodeContainer, Node node, long id) {
        super(nodeContainerFactory, nodeContainer, node, id);
    }

    protected EventNode getEventNode() {
        return (EventNode) getNode();
    }

    public T variableName(String variableName) {
        getEventNode().setVariableName(variableName);
        return (T) this;
    }

    public T inputVariableName(String variableName) {
        getEventNode().setInputVariableName(variableName);
        return (T) this;
    }

    public T eventFilter(EventFilter eventFilter) {
        getEventNode().addEventFilter(eventFilter);
        return (T) this;
    }

    public T eventType(String eventType) {
        EventTypeFilter filter = new EventTypeFilter();
        filter.setType(eventType);
        return eventFilter(filter);
    }

    public T scope(String scope) {
        getEventNode().setScope(scope);
        return (T) this;
    }
}

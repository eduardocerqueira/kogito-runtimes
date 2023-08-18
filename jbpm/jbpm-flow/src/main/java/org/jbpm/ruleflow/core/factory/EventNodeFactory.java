package org.jbpm.ruleflow.core.factory;

import org.jbpm.ruleflow.core.RuleFlowNodeContainerFactory;
import org.jbpm.workflow.core.NodeContainer;
import org.jbpm.workflow.core.node.EventNode;

public class EventNodeFactory<T extends RuleFlowNodeContainerFactory<T, ?>> extends AbstractEventNodeFactory<EventNodeFactory<T>, T> {

    public static final String METHOD_EVENT_TYPE = "eventType";
    public static final String METHOD_SCOPE = "scope";
    public static final String METHOD_VARIABLE_NAME = "variableName";
    public static final String METHOD_INPUT_VARIABLE_NAME = "inputVariableName";

    public EventNodeFactory(T nodeContainerFactory, NodeContainer nodeContainer, long id) {
        super(nodeContainerFactory, nodeContainer, new EventNode(), id);
    }

    @Override
    protected EventNode getEventNode() {
        return (EventNode) getNode();
    }

}

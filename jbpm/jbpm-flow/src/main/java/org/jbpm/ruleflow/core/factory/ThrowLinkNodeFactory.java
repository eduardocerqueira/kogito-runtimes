package org.jbpm.ruleflow.core.factory;

import org.jbpm.ruleflow.core.RuleFlowNodeContainerFactory;
import org.jbpm.workflow.core.NodeContainer;
import org.jbpm.workflow.core.node.ThrowLinkNode;

public class ThrowLinkNodeFactory<T extends RuleFlowNodeContainerFactory<T, ?>> extends ExtendedNodeFactory<ThrowLinkNodeFactory<T>, T> {

    public ThrowLinkNodeFactory(T nodeContainerFactory, NodeContainer nodeContainer, long id) {
        super(nodeContainerFactory, nodeContainer, new ThrowLinkNode(), id);
    }

}

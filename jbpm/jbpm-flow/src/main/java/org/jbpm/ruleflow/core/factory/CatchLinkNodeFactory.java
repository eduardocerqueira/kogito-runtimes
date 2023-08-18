package org.jbpm.ruleflow.core.factory;

import org.jbpm.ruleflow.core.RuleFlowNodeContainerFactory;
import org.jbpm.workflow.core.NodeContainer;
import org.jbpm.workflow.core.node.CatchLinkNode;

public class CatchLinkNodeFactory<T extends RuleFlowNodeContainerFactory<T, ?>> extends ExtendedNodeFactory<CatchLinkNodeFactory<T>, T> {

    public CatchLinkNodeFactory(T nodeContainerFactory, NodeContainer nodeContainer, long id) {
        super(nodeContainerFactory, nodeContainer, new CatchLinkNode(), id);
    }
}

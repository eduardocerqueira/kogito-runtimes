package org.jbpm.ruleflow.core.factory;

import java.util.ArrayList;
import java.util.List;

import org.jbpm.ruleflow.core.RuleFlowNodeContainerFactory;
import org.jbpm.workflow.core.DroolsAction;
import org.jbpm.workflow.core.Node;
import org.jbpm.workflow.core.NodeContainer;
import org.jbpm.workflow.core.impl.DroolsConsequenceAction;
import org.jbpm.workflow.core.impl.ExtendedNodeImpl;

public abstract class ExtendedNodeFactory<T extends NodeFactory<T, P>, P extends RuleFlowNodeContainerFactory<P, ?>> extends NodeFactory<T, P> {

    protected ExtendedNodeFactory(P nodeContainerFactory, NodeContainer nodeContainer, Node node, long id) {
        super(nodeContainerFactory, nodeContainer, node, id);
    }

    protected ExtendedNodeImpl getExtendedNode() {
        return (ExtendedNodeImpl) getNode();
    }

    public T onEntryAction(String dialect, String action) {
        if (getExtendedNode().getActions(dialect) != null) {
            getExtendedNode().getActions(dialect).add(new DroolsConsequenceAction(dialect, action));
        } else {
            List<DroolsAction> actions = new ArrayList<>();
            actions.add(new DroolsConsequenceAction(dialect, action));
            getExtendedNode().setActions(ExtendedNodeImpl.EVENT_NODE_ENTER, actions);
        }
        return (T) this;
    }

    public T onExitAction(String dialect, String action) {
        if (getExtendedNode().getActions(dialect) != null) {
            getExtendedNode().getActions(dialect).add(new DroolsConsequenceAction(dialect, action));
        } else {
            List<DroolsAction> actions = new ArrayList<>();
            actions.add(new DroolsConsequenceAction(dialect, action));
            getExtendedNode().setActions(ExtendedNodeImpl.EVENT_NODE_EXIT, actions);
        }
        return (T) this;
    }
}

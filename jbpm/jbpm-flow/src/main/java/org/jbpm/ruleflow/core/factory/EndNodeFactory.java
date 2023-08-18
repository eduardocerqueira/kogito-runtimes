package org.jbpm.ruleflow.core.factory;

import java.util.ArrayList;
import java.util.List;

import org.jbpm.process.instance.impl.Action;
import org.jbpm.ruleflow.core.RuleFlowNodeContainerFactory;
import org.jbpm.workflow.core.DroolsAction;
import org.jbpm.workflow.core.NodeContainer;
import org.jbpm.workflow.core.impl.ExtendedNodeImpl;
import org.jbpm.workflow.core.node.EndNode;

import static org.jbpm.ruleflow.core.Metadata.ACTION;

public class EndNodeFactory<T extends RuleFlowNodeContainerFactory<T, ?>> extends NodeFactory<EndNodeFactory<T>, T> implements SupportsAction<EndNodeFactory<T>, T> {

    public static final String METHOD_TERMINATE = "terminate";
    public static final String METHOD_ACTION = "action";

    public EndNodeFactory(T nodeContainerFactory, NodeContainer nodeContainer, long id) {
        super(nodeContainerFactory, nodeContainer, new EndNode(), id);
    }

    protected EndNode getEndNode() {
        return (EndNode) getNode();
    }

    public EndNodeFactory<T> terminate(boolean terminate) {
        getEndNode().setTerminate(terminate);
        return this;
    }

    @Override
    public EndNodeFactory<T> action(Action action) {
        DroolsAction droolsAction = new DroolsAction();
        droolsAction.setMetaData(ACTION, action);
        List<DroolsAction> enterActions = getEndNode().getActions(ExtendedNodeImpl.EVENT_NODE_ENTER);
        if (enterActions == null) {
            enterActions = new ArrayList<>();
            getEndNode().setActions(ExtendedNodeImpl.EVENT_NODE_ENTER, enterActions);
        }
        enterActions.add(droolsAction);
        return this;
    }
}

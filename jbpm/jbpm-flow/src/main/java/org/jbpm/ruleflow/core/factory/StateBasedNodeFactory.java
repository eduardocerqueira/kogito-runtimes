package org.jbpm.ruleflow.core.factory;

import org.jbpm.process.core.timer.Timer;
import org.jbpm.ruleflow.core.RuleFlowNodeContainerFactory;
import org.jbpm.workflow.core.Node;
import org.jbpm.workflow.core.NodeContainer;
import org.jbpm.workflow.core.impl.DroolsConsequenceAction;
import org.jbpm.workflow.core.node.StateBasedNode;

public abstract class StateBasedNodeFactory<T extends StateBasedNodeFactory<T, P>, P extends RuleFlowNodeContainerFactory<P, ?>> extends ExtendedNodeFactory<T, P> {

    public static final String METHOD_TIMER = "timer";

    protected StateBasedNodeFactory(P nodeContainerFactory, NodeContainer nodeContainer, Node node, long id) {
        super(nodeContainerFactory, nodeContainer, node, id);
    }

    protected StateBasedNode getStateBasedNode() {
        return (StateBasedNode) node;
    }

    public StateBasedNodeFactory<T, P> timer(String delay, String period, String dialect, String action) {
        Timer timer = new Timer();
        timer.setDelay(delay);
        timer.setPeriod(period);
        getStateBasedNode().addTimer(timer, new DroolsConsequenceAction(dialect, action));
        return this;
    }
}
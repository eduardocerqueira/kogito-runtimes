package org.jbpm.ruleflow.core.factory;

import org.jbpm.ruleflow.core.RuleFlowNodeContainerFactory;
import org.jbpm.workflow.core.NodeContainer;
import org.jbpm.workflow.core.impl.ConnectionRef;
import org.jbpm.workflow.core.impl.ConstraintImpl;
import org.jbpm.workflow.core.node.StateNode;

import static org.jbpm.workflow.core.Node.CONNECTION_DEFAULT_TYPE;

public class StateNodeFactory<T extends RuleFlowNodeContainerFactory<T, ?>> extends AbstractCompositeNodeFactory<StateNodeFactory<T>, T> {

    public static final String METHOD_CONSTRAINT = "constraint";

    public StateNodeFactory(T nodeContainerFactory, NodeContainer nodeContainer, long id) {
        super(nodeContainerFactory, nodeContainer, new StateNode(), id);
    }

    protected StateNode getStateNode() {
        return (StateNode) node;
    }

    public StateNodeFactory<T> constraint(String connectionId, long nodeId, String type, String dialect, String constraint, int priority) {
        ConstraintImpl constraintImpl = new ConstraintImpl();
        constraintImpl.setName(connectionId);
        constraintImpl.setType(type);
        constraintImpl.setDialect(dialect);
        constraintImpl.setConstraint(constraint);
        constraintImpl.setPriority(priority);
        getStateNode().addConstraint(
                new ConnectionRef(connectionId, nodeId, CONNECTION_DEFAULT_TYPE),
                constraintImpl);
        return this;
    }
}

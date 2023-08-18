package org.jbpm.ruleflow.core.factory;

import org.jbpm.ruleflow.core.RuleFlowNodeContainerFactory;
import org.jbpm.workflow.core.NodeContainer;
import org.jbpm.workflow.core.node.FaultNode;

public class FaultNodeFactory<T extends RuleFlowNodeContainerFactory<T, ?>> extends ExtendedNodeFactory<FaultNodeFactory<T>, T> {

    public static final String METHOD_FAULT_NAME = "faultName";
    public static final String METHOD_FAULT_VARIABLE = "faultVariable";
    public static final String METHOD_TERMINATE_PARENT = "terminateParent";

    public FaultNodeFactory(T nodeContainerFactory, NodeContainer nodeContainer, long id) {
        super(nodeContainerFactory, nodeContainer, new FaultNode(), id);
    }

    protected FaultNode getFaultNode() {
        return (FaultNode) getNode();
    }

    public FaultNodeFactory<T> faultVariable(String faultVariable) {
        ((FaultNode) getNode()).setFaultVariable(faultVariable);
        return this;
    }

    public FaultNodeFactory<T> faultName(String faultName) {
        ((FaultNode) getNode()).setFaultName(faultName);
        return this;
    }

    public FaultNodeFactory<T> terminateParent(Boolean terminateParent) {
        getFaultNode().setTerminateParent(terminateParent);
        return this;
    }
}

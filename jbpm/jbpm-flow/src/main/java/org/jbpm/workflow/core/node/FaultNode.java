package org.jbpm.workflow.core.node;

import org.jbpm.workflow.core.Node;
import org.jbpm.workflow.core.impl.ExtendedNodeImpl;
import org.kie.api.definition.process.Connection;

/**
 * Default implementation of a fault node.
 * 
 */
public class FaultNode extends ExtendedNodeImpl {

    private static final String[] EVENT_TYPES =
            new String[] { EVENT_NODE_ENTER };

    private static final long serialVersionUID = 510l;

    private String faultName;
    private String faultVariable;
    private boolean terminateParent = false;

    public String getFaultVariable() {
        return faultVariable;
    }

    public void setFaultVariable(String faultVariable) {
        this.faultVariable = faultVariable;
    }

    public String getFaultName() {
        return faultName;
    }

    public void setFaultName(String faultName) {
        this.faultName = faultName;
    }

    public boolean isTerminateParent() {
        return terminateParent;
    }

    public void setTerminateParent(boolean terminateParent) {
        this.terminateParent = terminateParent;
    }

    @Override
    public String[] getActionTypes() {
        return EVENT_TYPES;
    }

    @Override
    public void validateAddIncomingConnection(final String type, final Connection connection) {
        super.validateAddIncomingConnection(type, connection);
        if (!Node.CONNECTION_DEFAULT_TYPE.equals(type)) {
            throw new IllegalArgumentException(
                    "This type of node [" + connection.getTo().getMetaData().get("UniqueId") + ", " + connection.getTo().getName()
                            + "] only accepts default incoming connection type!");
        }
        if (getFrom() != null) {
            throw new IllegalArgumentException(
                    "This type of node [" + connection.getTo().getMetaData().get("UniqueId") + ", " + connection.getTo().getName()
                            + "] cannot have more than one incoming connection!");
        }
    }

    @Override
    public void validateAddOutgoingConnection(final String type, final Connection connection) {
        throw new UnsupportedOperationException(
                "A fault node does not have an outgoing connection!");
    }

    @Override
    public void validateRemoveOutgoingConnection(final String type, final Connection connection) {
        throw new UnsupportedOperationException(
                "A fault node does not have an outgoing connection!");
    }
}

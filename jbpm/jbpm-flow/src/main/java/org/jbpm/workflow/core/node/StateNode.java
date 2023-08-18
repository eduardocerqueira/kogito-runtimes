package org.jbpm.workflow.core.node;

import java.util.HashMap;
import java.util.Map;

import org.jbpm.workflow.core.Constraint;
import org.jbpm.workflow.core.impl.ConnectionRef;
import org.kie.api.definition.process.Connection;

public class StateNode extends CompositeContextNode implements Constrainable {

    private static final long serialVersionUID = 510l;

    private Map<ConnectionRef, Constraint> constraints = new HashMap<>();

    public void setConstraints(Map<ConnectionRef, Constraint> constraints) {
        this.constraints = constraints;
    }

    @Override
    public void setConstraint(final Connection connection, final Constraint constraint) {
        if (connection == null) {
            throw new IllegalArgumentException("connection is null");
        }
        if (!getDefaultOutgoingConnections().contains(connection)) {
            throw new IllegalArgumentException("connection is unknown:" + connection);
        }
        addConstraint(new ConnectionRef((String) connection.getMetaData().get("UniqueId"),
                connection.getTo().getId(), connection.getToType()), constraint);
    }

    @Override
    public void addConstraint(ConnectionRef connectionRef, Constraint constraint) {
        if (connectionRef == null) {
            throw new IllegalArgumentException(
                    "A state node only accepts constraints linked to a connection");
        }
        constraints.put(connectionRef, constraint);
    }

    public Constraint getConstraint(ConnectionRef connectionRef) {
        return constraints.get(connectionRef);
    }

    @Override
    public Map<ConnectionRef, Constraint> getConstraints() {
        return constraints;
    }

    @Override
    public Constraint getConstraint(final Connection connection) {
        if (connection == null) {
            throw new IllegalArgumentException("connection is null");
        }
        ConnectionRef ref = new ConnectionRef((String) connection.getMetaData().get("UniqueId"), connection.getTo().getId(), connection.getToType());
        return this.constraints.get(ref);
    }

}

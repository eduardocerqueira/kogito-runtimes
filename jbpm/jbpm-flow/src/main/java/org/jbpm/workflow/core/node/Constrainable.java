package org.jbpm.workflow.core.node;

import org.jbpm.workflow.core.Constraint;
import org.jbpm.workflow.core.impl.ConnectionRef;

public interface Constrainable {

    /**
     * Adds the given constraint.
     * In cases where the constraint is associated with a specific connection,
     * this connection will be identified using a ConnectionRef. In other cases
     * the ConnectionRef will be null and can be ignored.
     * 
     * @param connection
     * @param constraint
     */
    public void addConstraint(ConnectionRef connection, Constraint constraint);

}

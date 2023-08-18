package org.jbpm.ruleflow.core.factory;

import org.jbpm.workflow.core.Node;
import org.jbpm.workflow.core.impl.ConnectionRef;
import org.jbpm.workflow.core.impl.ConstraintImpl;

public class ConstraintFactory<T extends SplitFactory<?>> {
    private T parent;
    private ConstraintImpl constraintImpl;

    public ConstraintFactory(T parent, long toNodeId, String name, String type, String dialect, String constraint) {
        this.parent = parent;
        constraintImpl = new ConstraintImpl();
        constraintImpl.setName(name);
        constraintImpl.setType(type);
        constraintImpl.setDialect(dialect);
        constraintImpl.setConstraint(constraint);
        parent.getSplit().addConstraint(
                new ConnectionRef(name, toNodeId, Node.CONNECTION_DEFAULT_TYPE), constraintImpl);
    }

    public ConstraintFactory<T> priority(int priority) {
        constraintImpl.setPriority(priority);
        return this;
    }

    public ConstraintFactory<T> withDefault(boolean def) {
        constraintImpl.setDefault(def);
        return this;
    }

    public ConstraintFactory<T> metadata(String name, Object value) {
        constraintImpl.setMetaData(name, value);
        return this;
    }

    T done() {
        return parent;
    }

}

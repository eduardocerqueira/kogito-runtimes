package org.jbpm.process.core.context.exception;

import java.io.Serializable;

import org.kie.api.definition.process.Node;

public class CompensationHandler implements ExceptionHandler, Serializable {

    private static final long serialVersionUID = 510l;

    private Node node;

    public Node getnode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    public String toString() {
        return "Compensation Handler [" + this.node.getName() + ", " + this.node.getMetaData().get("UniqueId") + "]";
    }

}

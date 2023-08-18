package org.jbpm.ruleflow.core.factory;

import org.jbpm.process.core.context.variable.Mappable;
import org.jbpm.ruleflow.core.RuleFlowNodeContainerFactory;
import org.jbpm.workflow.core.Node;
import org.jbpm.workflow.core.NodeContainer;

public abstract class NodeFactory<T extends NodeFactory<T, P>, P extends RuleFlowNodeContainerFactory<P, ?>> implements MappableNodeFactory<T> {

    public static final String METHOD_NAME = "name";
    public static final String METHOD_METADATA = "metaData";
    public static final String METHOD_DONE = "done";

    protected Object node;
    protected NodeContainer nodeContainer;
    protected P nodeContainerFactory;

    protected NodeFactory(P nodeContainerFactory, NodeContainer nodeContainer, Object node, Object id) {
        this.nodeContainerFactory = nodeContainerFactory;
        this.nodeContainer = nodeContainer;
        this.node = node;
        setId(node, id);
        if (node instanceof Node) {
            nodeContainer.addNode((Node) node);
        }
    }

    protected void setId(Object node, Object id) {
        ((Node) node).setId((long) id);
    }

    public Node getNode() {
        return (Node) node;
    }

    public T name(String name) {
        getNode().setName(name);
        return (T) this;
    }

    public T metaData(String name, Object value) {
        getNode().setMetaData(name, value);
        return (T) this;
    }

    public P done() {
        return this.nodeContainerFactory;
    }

    @Override
    public Mappable getMappableNode() {
        return (Mappable) node;
    }
}

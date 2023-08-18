package org.jbpm.workflow.core.impl;

import java.util.LinkedHashMap;
import java.util.Map;

import org.jbpm.process.core.Context;
import org.jbpm.workflow.core.NodeContainer;
import org.kie.api.definition.process.Node;

/**
 * 
 */
public class NodeContainerImpl implements NodeContainer {

    private static final long serialVersionUID = 510l;

    private Map<Long, Node> nodes;

    public NodeContainerImpl() {
        // keeping insertion order is useful for debugging and testing purposes.
        // tests can assume certain order to verify the nodes generated are the right ones, otherwise they will have to traverse the whole list.
        // if the list of nodes is printed on logs we can see the order in which the nodes were added by the parser.
        this.nodes = new LinkedHashMap<>();
    }

    @Override
    public void addNode(final Node node) {
        validateAddNode(node);
        if (!this.nodes.containsValue(node)) {
            this.nodes.put(node.getId(), node);
        }
    }

    protected void validateAddNode(Node node) {
        if (node == null) {
            throw new IllegalArgumentException("Node is null!");
        }
    }

    @Override
    public Node[] getNodes() {
        return this.nodes.values()
                .toArray(new Node[this.nodes.size()]);
    }

    @Override
    public Node getNode(final long id) {
        Node node = this.nodes.get(id);
        if (node == null) {
            throw new IllegalArgumentException("Unknown node id: " + id);
        }
        return node;
    }

    @Override
    public Node getNodeByUniqueId(String s) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Node internalGetNode(long id) {
        return getNode(id);
    }

    @Override
    public void removeNode(final Node node) {
        validateRemoveNode(node);
        this.nodes.remove(node.getId());
    }

    protected void validateRemoveNode(Node node) {
        if (node == null) {
            throw new IllegalArgumentException("Node is null");
        }
        if (this.nodes.get(node.getId()) == null) {
            throw new IllegalArgumentException("Unknown node: " + node);
        }
    }

    @Override
    public Context resolveContext(String contextId, Object param) {
        return null;
    }

}

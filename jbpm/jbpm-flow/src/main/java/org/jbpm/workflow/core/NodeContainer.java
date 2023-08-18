package org.jbpm.workflow.core;

import java.io.Serializable;

import org.jbpm.process.core.Context;
import org.kie.api.definition.process.Node;

public interface NodeContainer extends org.kie.api.definition.process.NodeContainer, Serializable {

    /**
     * Method for adding a node to this node container.
     * Note that the node will get an id unique for this node container.
     * 
     * @param node the node to be added
     * @throws IllegalArgumentException if <code>node</code> is null
     */
    void addNode(Node node);

    /**
     * Method for removing a node from this node container
     * 
     * @param node the node to be removed
     * @throws IllegalArgumentException if <code>node</code> is null or unknown
     */
    void removeNode(Node node);

    Context resolveContext(String contextId, Object param);

    Node internalGetNode(long id);

}

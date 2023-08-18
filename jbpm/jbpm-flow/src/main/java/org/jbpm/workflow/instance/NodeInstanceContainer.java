package org.jbpm.workflow.instance;

import java.util.Collection;

import org.jbpm.workflow.core.node.AsyncEventNode;
import org.kie.api.definition.process.Node;
import org.kie.api.definition.process.NodeContainer;
import org.kie.kogito.internal.process.runtime.KogitoNodeInstanceContainer;

import static org.jbpm.ruleflow.core.Metadata.CUSTOM_ASYNC;
import static org.jbpm.ruleflow.core.Metadata.UNIQUE_ID;

/**
 *
 */
public interface NodeInstanceContainer extends KogitoNodeInstanceContainer {

    Collection<NodeInstance> getNodeInstances(boolean recursive);

    NodeInstance getFirstNodeInstance(long nodeId);

    NodeInstance getNodeInstance(Node node);

    void addNodeInstance(NodeInstance nodeInstance);

    void removeNodeInstance(NodeInstance nodeInstance);

    NodeContainer getNodeContainer();

    void nodeInstanceCompleted(NodeInstance nodeInstance, String outType);

    int getState();

    void setState(int state);

    int getLevelForNode(String uniqueID);

    int getCurrentLevel();

    void setCurrentLevel(int level);

    NodeInstance getNodeInstance(String nodeInstanceId, boolean recursive);

    default NodeInstance getByNodeDefinitionId(final String nodeDefinitionId, NodeContainer nodeContainer) {
        for (Node node : nodeContainer.getNodes()) {

            if (nodeDefinitionId.equals(node.getMetaData().get(UNIQUE_ID))) {
                return getNodeInstance(node);
            }

            if (node instanceof NodeContainer) {
                NodeInstance ni = getByNodeDefinitionId(nodeDefinitionId, ((NodeContainer) node));

                if (ni != null) {
                    return ni;
                }
            }
        }

        throw new IllegalArgumentException("Node with definition id " + nodeDefinitionId + " was not found");
    }

    default Node resolveAsync(Node node) {
        // async continuation handling
        if (node instanceof AsyncEventNode) {
            return ((AsyncEventNode) node).getActualNode();
        }
        boolean asyncMode = Boolean.parseBoolean((String) node.getMetaData().get(CUSTOM_ASYNC));
        if (asyncMode) {
            return new AsyncEventNode(node);
        }
        return node;
    }
}

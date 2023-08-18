package org.kie.kogito.svg.model;

import java.util.HashMap;
import java.util.Map;

public class SVGSummary {

    private Map<String, NodeSummary> nodes = new HashMap<>();

    public void addNode(NodeSummary node) {
        String nodeId = node.getNodeId();
        if (nodeId == null) {
            throw new IllegalArgumentException("Node id cannot be null.");
        }
        nodes.put(node.getNodeId(), node);
    }

    public NodeSummary getNode(String nodeId) {
        return nodes.get(nodeId);
    }

    public Map<String, NodeSummary> getNodesMap() {
        return nodes;
    }
}
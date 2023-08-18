package org.jbpm.workflow.core.node;

import java.util.HashMap;
import java.util.Map;

import org.jbpm.workflow.core.impl.NodeImpl;
import org.kie.api.definition.process.Node;

public class AsyncEventNode extends EventNode {

    private static final long serialVersionUID = -4724021457443413412L;

    private Node node;

    public AsyncEventNode(Node node) {
        this.node = node;
    }

    public Node getActualNode() {
        return node;
    }

    @Override
    public long getId() {
        return node.getId();
    }

    @Override
    public Object getMetaData(String name) {
        return ((NodeImpl) node).getMetaData(name);
    }

    @Override
    public Map<String, Object> getMetaData() {
        Map<String, Object> metaData = new HashMap<>(node.getMetaData());
        metaData.put("hidden", "true");
        return metaData;
    }

}

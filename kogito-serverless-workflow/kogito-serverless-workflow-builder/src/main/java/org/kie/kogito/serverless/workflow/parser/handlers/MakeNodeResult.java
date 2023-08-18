package org.kie.kogito.serverless.workflow.parser.handlers;

import org.jbpm.ruleflow.core.factory.NodeFactory;

public class MakeNodeResult {

    private final NodeFactory<?, ?> incomingNode;
    private final NodeFactory<?, ?> outgoingNode;

    public MakeNodeResult(NodeFactory<?, ?> node) {
        this(node, node);
    }

    public MakeNodeResult(NodeFactory<?, ?> incomingNode, NodeFactory<?, ?> outgoingNode) {
        this.incomingNode = incomingNode;
        this.outgoingNode = outgoingNode;

    }

    public NodeFactory<?, ?> getIncomingNode() {
        return incomingNode;
    }

    public NodeFactory<?, ?> getOutgoingNode() {
        return outgoingNode;
    }
}

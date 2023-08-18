package org.jbpm.process.instance.impl.actions;

import org.jbpm.workflow.instance.node.StateBasedNodeInstance;
import org.kie.api.runtime.process.NodeInstance;

public class CompleteStateBasedNodeInstanceAction extends AbstractNodeInstanceAction {

    private static final long serialVersionUID = 1L;

    public CompleteStateBasedNodeInstanceAction(String attachedToNodeId) {
        super(attachedToNodeId);
    }

    @Override
    protected void execute(NodeInstance nodeInstance) {
        ((StateBasedNodeInstance) nodeInstance).triggerCompleted();
    }
}

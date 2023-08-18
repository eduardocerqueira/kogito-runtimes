package org.jbpm.process.instance.impl.actions;

import org.kie.api.runtime.process.NodeInstance;

public class CancelNodeInstanceAction extends AbstractNodeInstanceAction {

    private static final long serialVersionUID = 1L;

    public CancelNodeInstanceAction(String attachedToNodeId) {
        super(attachedToNodeId);
    }

    @Override
    protected void execute(NodeInstance nodeInstance) {
        ((org.jbpm.workflow.instance.NodeInstance) nodeInstance).cancel();
    }
}

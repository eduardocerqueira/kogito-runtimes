package org.jbpm.process.instance.impl.actions;

import java.io.Serializable;
import java.util.Collection;

import org.jbpm.process.instance.impl.Action;
import org.jbpm.workflow.instance.node.CompositeNodeInstance;
import org.kie.api.runtime.process.NodeInstance;
import org.kie.api.runtime.process.WorkflowProcessInstance;
import org.kie.kogito.internal.process.runtime.KogitoProcessContext;

public abstract class AbstractNodeInstanceAction implements Action, Serializable {

    private static final long serialVersionUID = 1L;

    private String attachedToNodeId;

    protected AbstractNodeInstanceAction(String attachedToNodeId) {
        this.attachedToNodeId = attachedToNodeId;
    }

    @Override
    public void execute(KogitoProcessContext context) throws Exception {
        WorkflowProcessInstance pi = context.getNodeInstance().getProcessInstance();
        NodeInstance nodeInstance = findNodeByUniqueId(pi.getNodeInstances(), attachedToNodeId);
        if (nodeInstance != null) {
            execute(nodeInstance);
        }
    }

    protected abstract void execute(NodeInstance nodeInstance);

    private static NodeInstance findNodeByUniqueId(Collection<NodeInstance> nodeInstances, String uniqueId) {
        if (nodeInstances != null && !nodeInstances.isEmpty()) {
            for (NodeInstance nInstance : nodeInstances) {
                String nodeUniqueId = (String) nInstance.getNode().getMetaData().get("UniqueId");
                if (uniqueId.equals(nodeUniqueId)) {
                    return nInstance;
                }
                if (nInstance instanceof CompositeNodeInstance) {
                    NodeInstance nodeInstance = findNodeByUniqueId(((CompositeNodeInstance) nInstance).getNodeInstances(), uniqueId);
                    if (nodeInstance != null) {
                        return nodeInstance;
                    }
                }
            }
        }
        return null;
    }
}
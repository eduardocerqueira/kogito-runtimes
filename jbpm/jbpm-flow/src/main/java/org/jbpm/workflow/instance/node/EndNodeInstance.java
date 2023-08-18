package org.jbpm.workflow.instance.node;

import java.util.Date;

import org.drools.core.common.InternalKnowledgeRuntime;
import org.jbpm.process.instance.InternalProcessRuntime;
import org.jbpm.workflow.core.Node;
import org.jbpm.workflow.core.node.EndNode;
import org.jbpm.workflow.instance.NodeInstanceContainer;
import org.jbpm.workflow.instance.impl.ExtendedNodeInstanceImpl;
import org.kie.kogito.internal.process.runtime.KogitoNodeInstance;
import org.kie.kogito.internal.process.runtime.KogitoProcessInstance;

import static org.jbpm.ruleflow.core.Metadata.HIDDEN;
import static org.jbpm.workflow.core.node.EndNode.PROCESS_SCOPE;

/**
 * Runtime counterpart of an end node.
 */
public class EndNodeInstance extends ExtendedNodeInstanceImpl {

    private static final long serialVersionUID = 510l;

    public EndNode getEndNode() {
        return (EndNode) getNode();
    }

    @Override
    public void internalTrigger(KogitoNodeInstance from, String type) {
        super.internalTrigger(from, type);
        if (!Node.CONNECTION_DEFAULT_TYPE.equals(type)) {
            throw new IllegalArgumentException(
                    "An EndNode only accepts default incoming connections!");
        }
        leaveTime = new Date();
        boolean hidden = false;
        if (getNode().getMetaData().get(HIDDEN) != null) {
            hidden = true;
        }
        InternalKnowledgeRuntime kruntime = getProcessInstance().getKnowledgeRuntime();
        if (!hidden) {
            ((InternalProcessRuntime) kruntime.getProcessRuntime())
                    .getProcessEventSupport().fireBeforeNodeLeft(this, kruntime);
        }
        ((NodeInstanceContainer) getNodeInstanceContainer()).removeNodeInstance(this);
        if (getEndNode().isTerminate()) {
            if (getNodeInstanceContainer() instanceof CompositeNodeInstance) {
                if (getEndNode().getScope() == PROCESS_SCOPE) {
                    getProcessInstance().setState(KogitoProcessInstance.STATE_COMPLETED);
                } else {
                    while (!getNodeInstanceContainer().getNodeInstances().isEmpty()) {
                        ((org.jbpm.workflow.instance.NodeInstance) getNodeInstanceContainer().getNodeInstances().iterator().next()).cancel();
                    }
                    ((NodeInstanceContainer) getNodeInstanceContainer()).nodeInstanceCompleted(this, null);
                }
            } else {
                ((NodeInstanceContainer) getNodeInstanceContainer()).setState(KogitoProcessInstance.STATE_COMPLETED);
            }

        } else {
            ((NodeInstanceContainer) getNodeInstanceContainer())
                    .nodeInstanceCompleted(this, null);
        }
        if (!hidden) {
            ((InternalProcessRuntime) kruntime.getProcessRuntime())
                    .getProcessEventSupport().fireAfterNodeLeft(this, kruntime);
        }
    }

}

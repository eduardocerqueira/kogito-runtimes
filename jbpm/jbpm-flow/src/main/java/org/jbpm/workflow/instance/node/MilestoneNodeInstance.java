package org.jbpm.workflow.instance.node;

import org.jbpm.util.ContextFactory;
import org.jbpm.workflow.core.Node;
import org.jbpm.workflow.core.node.MilestoneNode;
import org.kie.kogito.event.process.ContextAwareEventListener;
import org.kie.kogito.internal.process.runtime.KogitoNodeInstance;

/**
 * Runtime counterpart of a milestone node.
 */
public class MilestoneNodeInstance extends StateBasedNodeInstance {

    private static final long serialVersionUID = 510L;

    protected MilestoneNode getMilestoneNode() {
        return (MilestoneNode) getNode();
    }

    @Override
    public void internalTrigger(KogitoNodeInstance from, String type) {
        super.internalTrigger(from, type);
        // if node instance was cancelled, abort
        if (getNodeInstanceContainer().getNodeInstance(getStringId()) == null) {
            return;
        }
        if (!Node.CONNECTION_DEFAULT_TYPE.equals(type)) {
            throw new IllegalArgumentException(
                    "A MilestoneNode only accepts default incoming connections!");
        }
        if (isCompleted()) {
            triggerCompleted();
        } else {
            addCompletionEventListener();
        }
    }

    private boolean isCompleted() {
        return getMilestoneNode().canComplete(ContextFactory.fromNode(this));
    }

    @Override
    public void addEventListeners() {
        super.addEventListeners();
        addCompletionEventListener();
    }

    private void addCompletionEventListener() {
        getProcessInstance().getKnowledgeRuntime().getProcessRuntime().addEventListener(ContextAwareEventListener.using(listener -> {
            if (isCompleted()) {
                triggerCompleted();
                getProcessInstance().getKnowledgeRuntime().getProcessRuntime().removeEventListener(listener);
            }
        }));
    }

    @Override
    public void removeEventListeners() {
        super.removeEventListeners();
        getProcessInstance().removeEventListener(getActivationEventType(), this, true);
    }

    private String getActivationEventType() {
        return "RuleFlow-Milestone-" + getProcessInstance().getProcessId()
                + "-" + getMilestoneNode().getUniqueId();
    }
}

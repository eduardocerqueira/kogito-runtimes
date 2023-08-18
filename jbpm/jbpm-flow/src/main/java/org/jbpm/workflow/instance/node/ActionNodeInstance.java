package org.jbpm.workflow.instance.node;

import java.util.Date;
import java.util.Map;

import org.jbpm.process.instance.KogitoProcessContextImpl;
import org.jbpm.process.instance.impl.Action;
import org.jbpm.util.ContextFactory;
import org.jbpm.workflow.core.Node;
import org.jbpm.workflow.core.impl.NodeIoHelper;
import org.jbpm.workflow.core.node.ActionNode;
import org.jbpm.workflow.instance.WorkflowRuntimeException;
import org.jbpm.workflow.instance.impl.NodeInstanceImpl;
import org.kie.kogito.internal.process.runtime.KogitoNodeInstance;

/**
 * Runtime counterpart of an action node.
 * 
 */
public class ActionNodeInstance extends NodeInstanceImpl {

    private static final long serialVersionUID = 510l;

    protected ActionNode getActionNode() {
        return (ActionNode) getNode();
    }

    @Override
    public void internalTrigger(KogitoNodeInstance from, String type) {
        triggerTime = new Date();
        if (!Node.CONNECTION_DEFAULT_TYPE.equals(type)) {
            throw new IllegalArgumentException(
                    "An ActionNode only accepts default incoming connections!");
        }

        Map<String, Object> data = NodeIoHelper.processInputs(this, key -> getVariable(key));

        Action action = (Action) getActionNode().getAction().getMetaData("Action");
        try {

            KogitoProcessContextImpl context = ContextFactory.fromNode(this);
            context.setContextData(data);
            executeAction(action, context);

        } catch (WorkflowRuntimeException wre) {
            throw wre;
        } catch (Exception e) {
            // for the case that one of the following throws an exception
            // - the ProcessContext() constructor 
            // - or context.setNodeInstance(this) 
            throw new WorkflowRuntimeException(this, getProcessInstance(), "Unable to execute Action: " + e.getMessage(), e);
        }
        triggerCompleted();
    }

    public void triggerCompleted() {
        triggerCompleted(Node.CONNECTION_DEFAULT_TYPE, true);
    }

}

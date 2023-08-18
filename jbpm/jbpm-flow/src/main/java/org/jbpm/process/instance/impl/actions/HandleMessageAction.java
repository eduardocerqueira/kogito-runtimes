package org.jbpm.process.instance.impl.actions;

import java.io.Serializable;
import java.util.Map;

import org.jbpm.process.instance.impl.Action;
import org.jbpm.ruleflow.core.Metadata;
import org.jbpm.workflow.core.impl.NodeIoHelper;
import org.jbpm.workflow.instance.impl.NodeInstanceImpl;
import org.kie.kogito.internal.process.runtime.KogitoProcessContext;
import org.kie.kogito.process.workitems.InternalKogitoWorkItemManager;
import org.kie.kogito.process.workitems.impl.KogitoWorkItemImpl;

public class HandleMessageAction implements Action, Serializable {

    private static final long serialVersionUID = 1L;

    private final String messageType;
    private String variableName;

    public HandleMessageAction(String messageType, String variableName) {
        this.messageType = messageType;
        this.variableName = variableName;
    }

    @Override
    public void execute(KogitoProcessContext context) throws Exception {
        KogitoWorkItemImpl workItem = new KogitoWorkItemImpl();
        workItem.setName("Send Task");
        workItem.setNodeInstanceId((context.getNodeInstance()).getStringId());
        workItem.setProcessInstanceId((context.getProcessInstance()).getStringId());
        workItem.setNodeId(context.getNodeInstance().getNodeId());
        workItem.setParameter(Metadata.MESSAGE_TYPE, messageType);

        // compute inputs for message action
        NodeInstanceImpl impl = ((NodeInstanceImpl) context.getNodeInstance());
        Map<String, Object> inputSet = NodeIoHelper.processInputs(impl, varRef -> impl.getVariable(varRef));
        workItem.getParameters().put(variableName, inputSet.get(variableName));

        ((InternalKogitoWorkItemManager) context.getKogitoProcessRuntime().getKogitoWorkItemManager()).internalExecuteWorkItem(workItem);
    }

}

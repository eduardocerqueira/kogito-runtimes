package org.kie.kogito.serverless.workflow;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import org.kie.kogito.internal.process.runtime.KogitoWorkItem;

import static org.kie.kogito.serverless.workflow.SWFConstants.CONTENT_DATA;
import static org.kie.kogito.serverless.workflow.SWFConstants.MODEL_WORKFLOW_VAR;
import static org.kie.kogito.serverless.workflow.SWFConstants.SERVICE_IMPL_KEY;
import static org.kie.kogito.serverless.workflow.SWFConstants.WORKITEM_INTERFACE;
import static org.kie.kogito.serverless.workflow.SWFConstants.WORKITEM_INTERFACE_IMPL;
import static org.kie.kogito.serverless.workflow.SWFConstants.WORKITEM_OPERATION;
import static org.kie.kogito.serverless.workflow.SWFConstants.WORKITEM_OPERATION_IMPL;

public abstract class ServiceWorkItemHandler extends WorkflowWorkItemHandler {

    private static final Collection<String> keysToRemove = Set.of(SERVICE_IMPL_KEY, WORKITEM_OPERATION_IMPL, WORKITEM_INTERFACE_IMPL);

    @Override
    protected Object internalExecute(KogitoWorkItem workItem, Map<String, Object> parameters) {
        String className = (String) parameters.remove(WORKITEM_INTERFACE);
        String methodName = (String) parameters.remove(WORKITEM_OPERATION);
        parameters.keySet().removeAll(keysToRemove);
        Object arguments;
        int size = parameters.size();
        if (size == 0) {
            arguments = workItem.getParameter(MODEL_WORKFLOW_VAR);
        } else if (parameters.size() == 1 && parameters.containsKey(CONTENT_DATA)) {
            arguments = parameters.get(CONTENT_DATA);
        } else {
            arguments = parameters;
        }
        return invoke(className, methodName, arguments);
    }

    protected abstract Object invoke(String className, String methodName, Object parameters);
}

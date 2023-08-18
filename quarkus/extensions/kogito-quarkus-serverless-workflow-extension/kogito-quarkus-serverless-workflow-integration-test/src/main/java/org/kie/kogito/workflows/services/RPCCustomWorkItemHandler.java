package org.kie.kogito.workflows.services;

import java.util.Map;

import javax.enterprise.context.ApplicationScoped;

import org.kie.kogito.internal.process.runtime.KogitoWorkItem;
import org.kie.kogito.serverless.workflow.WorkflowWorkItemHandler;

@ApplicationScoped
public class RPCCustomWorkItemHandler extends WorkflowWorkItemHandler {

    public static final String NAME = "RPCCustomWorkItemHandler";
    public static final String OPERATION = "operation";

    @Override
    protected Object internalExecute(KogitoWorkItem workItem, Map<String, Object> parameters) {
        String operationId = (String) workItem.getNodeInstance().getNode().getMetaData().get(OPERATION);
        if (!"division".equals(operationId)) {
            throw new IllegalArgumentException("Operation " + operationId + " is not supported");
        }
        return (Integer) parameters.get("dividend") / (Integer) parameters.get("divisor");
    }

    @Override
    public String getName() {
        return NAME;
    }
}

package org.kie.kogito.serverless.workflow.operationid;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.ServiceLoader;

public class WorkflowOperationIdFactoryProvider {

    public static final String PROPERTY_NAME = "kogito.sw.operationIdStrategy";

    private static final Map<String, WorkflowOperationIdFactory> operationIds = new HashMap<>();

    static {
        for (WorkflowOperationIdFactory factory : ServiceLoader.load(WorkflowOperationIdFactory.class)) {
            for (String propName : factory.propertyValues()) {
                operationIds.put(propName, factory);
            }
        }
    }

    public static WorkflowOperationIdFactory getFactory(Optional<String> propValue) {
        Optional<WorkflowOperationIdFactory> factory = propValue.map(String::toUpperCase).map(operationIds::get);
        return factory.orElse(operationIds.get(FileNameWorkflowOperationIdFactory.FILE_PROP_VALUE));
    }

    private WorkflowOperationIdFactoryProvider() {
    }
}

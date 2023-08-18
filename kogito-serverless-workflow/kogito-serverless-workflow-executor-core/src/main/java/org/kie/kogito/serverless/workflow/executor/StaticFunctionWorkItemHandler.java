package org.kie.kogito.serverless.workflow.executor;

import java.util.Map;
import java.util.function.Function;

import org.kie.kogito.internal.process.runtime.KogitoWorkItem;
import org.kie.kogito.jackson.utils.JsonObjectUtils;
import org.kie.kogito.serverless.workflow.SWFConstants;
import org.kie.kogito.serverless.workflow.WorkflowWorkItemHandler;

import com.fasterxml.jackson.databind.JsonNode;

import static org.kie.kogito.serverless.workflow.SWFConstants.CONTENT_DATA;

public class StaticFunctionWorkItemHandler<V, T> extends WorkflowWorkItemHandler {

    private final String name;
    private final Function<V, T> function;

    public StaticFunctionWorkItemHandler(String name, Function<V, T> function) {
        this.name = name;
        this.function = function;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    protected Object internalExecute(KogitoWorkItem workItem, Map<String, Object> parameters) {
        int size = parameters.size();
        if (size == 0) {
            return function.apply((V) JsonObjectUtils.toJavaValue((JsonNode) workItem.getParameter(SWFConstants.MODEL_WORKFLOW_VAR)));
        } else if (size == 1 && parameters.containsKey(CONTENT_DATA)) {
            return function.apply((V) parameters.get(CONTENT_DATA));
        } else {
            return function.apply((V) parameters);
        }
    }
}

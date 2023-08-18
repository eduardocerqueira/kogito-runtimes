package org.kie.kogito.serverless.workflow.rest;

import java.util.Map;

import org.kogito.workitem.rest.bodybuilders.DefaultWorkItemHandlerBodyBuilder;

import static org.kie.kogito.serverless.workflow.SWFConstants.MODEL_WORKFLOW_VAR;

public class ParamsRestWorkItemHandlerBodyBuilder extends DefaultWorkItemHandlerBodyBuilder {

    @Override
    protected Object buildFromParams(Map<String, Object> parameters) {
        Object inputModel = parameters.remove(MODEL_WORKFLOW_VAR);
        return parameters.isEmpty() ? inputModel : parameters;
    }
}

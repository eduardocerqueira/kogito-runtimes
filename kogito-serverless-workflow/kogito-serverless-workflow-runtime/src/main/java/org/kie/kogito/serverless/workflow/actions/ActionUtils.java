package org.kie.kogito.serverless.workflow.actions;

import org.kie.kogito.internal.process.runtime.KogitoProcessContext;
import org.kie.kogito.jackson.utils.JsonObjectUtils;

import com.fasterxml.jackson.databind.JsonNode;

import static org.kie.kogito.serverless.workflow.SWFConstants.DEFAULT_WORKFLOW_VAR;

class ActionUtils {

    private ActionUtils() {
    }

    protected static JsonNode getWorkflowData(KogitoProcessContext context) {
        return getJsonNode(context, DEFAULT_WORKFLOW_VAR);
    }

    protected static JsonNode getJsonNode(KogitoProcessContext context, String variableName) {
        return JsonObjectUtils.fromValue(context.getVariable(variableName));
    }
}

package org.kie.kogito.serverless.workflow.workitemparams;

import org.kie.kogito.internal.process.runtime.KogitoWorkItem;

import com.fasterxml.jackson.databind.JsonNode;

public class JsonNodeResolver extends ExpressionWorkItemResolver<JsonNode> {

    public JsonNodeResolver(String exprLang, Object expr, String paramName) {
        super(exprLang, expr, paramName);
    }

    @Override
    public JsonNode apply(KogitoWorkItem workItem) {
        return evalExpression(workItem);
    }
}

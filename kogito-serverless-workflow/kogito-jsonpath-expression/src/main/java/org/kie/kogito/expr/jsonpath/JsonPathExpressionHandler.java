package org.kie.kogito.expr.jsonpath;

import org.kie.kogito.process.expr.Expression;
import org.kie.kogito.serverless.workflow.utils.CachedExpressionHandler;

public class JsonPathExpressionHandler extends CachedExpressionHandler {

    @Override
    public Expression buildExpression(String expr) {
        return new JsonPathExpression(expr);
    }

    @Override
    public String lang() {
        return JsonPathExpression.LANG;
    }
}

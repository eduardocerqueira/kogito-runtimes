package org.kie.kogito.serverless.workflow.utils;

import java.util.Collections;
import java.util.Map;
import java.util.WeakHashMap;

import org.kie.kogito.process.expr.Expression;
import org.kie.kogito.process.expr.ExpressionHandler;

public abstract class CachedExpressionHandler implements ExpressionHandler {

    private Map<String, Expression> expressions = Collections.synchronizedMap(new WeakHashMap<>());

    @Override
    public Expression get(String expr) {
        return expressions.computeIfAbsent(ExpressionHandlerUtils.trimExpr(expr), this::buildExpression);
    }

    protected abstract Expression buildExpression(String expr);
}

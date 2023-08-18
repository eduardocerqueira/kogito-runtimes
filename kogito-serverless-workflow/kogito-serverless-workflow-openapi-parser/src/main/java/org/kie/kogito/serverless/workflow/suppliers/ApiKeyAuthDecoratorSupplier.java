package org.kie.kogito.serverless.workflow.suppliers;

import java.util.function.Supplier;

import org.kogito.workitem.rest.auth.ApiKeyAuthDecorator;

import com.github.javaparser.ast.expr.Expression;

import static org.jbpm.compiler.canonical.descriptors.ExpressionUtils.getObjectCreationExpr;

public class ApiKeyAuthDecoratorSupplier extends ApiKeyAuthDecorator implements Supplier<Expression> {

    private final Expression expression;

    public ApiKeyAuthDecoratorSupplier(String paramName, ApiKeyAuthDecorator.Location location) {
        super(paramName, location);
        expression = getObjectCreationExpr(ApiKeyAuthDecorator.class, paramName, location);
    }

    @Override
    public Expression get() {
        return expression;
    }
}

package org.kie.kogito.serverless.workflow.suppliers;

import java.util.function.Supplier;

import org.kogito.workitem.rest.auth.BearerTokenAuthDecorator;

import com.github.javaparser.ast.expr.Expression;

import static org.jbpm.compiler.canonical.descriptors.ExpressionUtils.getObjectCreationExpr;

public class BearerTokenAuthDecoratorSupplier extends BearerTokenAuthDecorator implements Supplier<Expression> {

    private final Expression expression;

    public BearerTokenAuthDecoratorSupplier() {
        expression = getObjectCreationExpr(BearerTokenAuthDecorator.class);
    }

    @Override
    public Expression get() {
        return expression;
    }
}

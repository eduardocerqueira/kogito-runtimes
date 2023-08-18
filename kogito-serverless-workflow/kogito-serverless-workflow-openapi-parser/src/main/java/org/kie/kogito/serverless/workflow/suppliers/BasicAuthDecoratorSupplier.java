package org.kie.kogito.serverless.workflow.suppliers;

import java.util.function.Supplier;

import org.kogito.workitem.rest.auth.BasicAuthDecorator;

import com.github.javaparser.ast.expr.Expression;

import static org.jbpm.compiler.canonical.descriptors.ExpressionUtils.getObjectCreationExpr;

public class BasicAuthDecoratorSupplier extends BasicAuthDecorator implements Supplier<Expression> {

    private final Expression expression;

    public BasicAuthDecoratorSupplier() {
        expression = getObjectCreationExpr(BasicAuthDecorator.class);
    }

    @Override
    public Expression get() {
        return expression;
    }
}

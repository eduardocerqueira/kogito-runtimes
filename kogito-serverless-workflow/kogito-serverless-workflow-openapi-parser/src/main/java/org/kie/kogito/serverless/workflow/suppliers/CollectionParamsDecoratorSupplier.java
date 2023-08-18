package org.kie.kogito.serverless.workflow.suppliers;

import java.util.Collection;
import java.util.function.Supplier;

import org.kogito.workitem.rest.decorators.CollectionParamsDecorator;

import com.github.javaparser.ast.expr.Expression;

import static org.jbpm.compiler.canonical.descriptors.ExpressionUtils.getObjectCreationExpr;

public class CollectionParamsDecoratorSupplier extends CollectionParamsDecorator implements Supplier<Expression> {

    private final Expression expression;

    public CollectionParamsDecoratorSupplier(Collection<String> headerParams, Collection<String> queryParams) {
        super(headerParams, queryParams);
        expression = getObjectCreationExpr(CollectionParamsDecorator.class, headerParams, queryParams);
    }

    @Override
    public Expression get() {
        return expression;
    }
}

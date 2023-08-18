package org.kie.kogito.serverless.workflow.suppliers;

import java.util.function.Supplier;

import org.kie.kogito.serverless.workflow.rest.ParamsRestWorkItemHandlerBodyBuilder;

import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.ObjectCreationExpr;

public class ParamsRestBodyBuilderSupplier extends ParamsRestWorkItemHandlerBodyBuilder implements Supplier<Expression> {

    @Override
    public Expression get() {
        return new ObjectCreationExpr()
                .setType(ParamsRestWorkItemHandlerBodyBuilder.class.getCanonicalName());
    }
}

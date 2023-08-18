package org.kie.kogito.serverless.workflow.suppliers;

import java.util.function.Supplier;

import org.jbpm.compiler.canonical.descriptors.ExpressionUtils;
import org.kie.kogito.serverless.workflow.workitemparams.ObjectResolver;

import com.github.javaparser.ast.expr.Expression;

public class ObjectResolverSupplier extends ObjectResolver implements Supplier<Expression> {

    private final Expression expression;

    public ObjectResolverSupplier(String exprLang, Object expr, String paramName) {
        super(exprLang, expr, paramName);
        expression = ExpressionUtils.getObjectCreationExpr(ObjectResolver.class, exprLang, expr, paramName);
    }

    @Override
    public Expression get() {
        return expression;
    }
}

package org.kie.kogito.serverless.workflow.suppliers;

import java.util.function.Supplier;

import org.jbpm.compiler.canonical.descriptors.ExpressionUtils;
import org.kie.kogito.serverless.workflow.workitemparams.ExpressionParametersFactory;

import com.github.javaparser.ast.expr.Expression;

public class ExpressionParametersFactorySupplier extends ExpressionParametersFactory implements Supplier<Expression> {

    private Expression expression;

    public ExpressionParametersFactorySupplier(String exprLang, Object object, String paramName) {
        super(exprLang, object, paramName);
        this.expression = ExpressionUtils.getObjectCreationExpr(ExpressionParametersFactory.class, exprLang, object, paramName);
    }

    @Override
    public Expression get() {
        return expression;
    }
}

package org.kie.kogito.serverless.workflow.suppliers;

import java.util.function.Supplier;

import org.jbpm.compiler.canonical.descriptors.ExpressionUtils;
import org.jbpm.process.instance.impl.ExpressionReturnValueEvaluator;

import com.github.javaparser.ast.expr.Expression;

public class ExpressionReturnValueEvaluatorSupplier extends ExpressionReturnValueEvaluator implements
        Supplier<Expression> {

    private final Expression expr;

    public ExpressionReturnValueEvaluatorSupplier(String lang, String expression, String rootName, Class<?> returnType) {
        super(lang, expression, rootName);
        expr = ExpressionUtils.getObjectCreationExpr(ExpressionReturnValueEvaluator.class, lang, expression, rootName, returnType);
    }

    @Override
    public Expression get() {
        return expr;
    }
}

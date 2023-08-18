package org.jbpm.compiler.canonical.descriptors;

import java.util.function.Supplier;

import org.jbpm.process.instance.impl.ExpressionReturnValueEvaluator;

import com.github.javaparser.ast.expr.Expression;

public class ExpressionReturnValueSupplier extends ExpressionReturnValueEvaluator implements Supplier<Expression> {

    private final Expression expression;

    public ExpressionReturnValueSupplier(String lang, String expr, String rootName) {
        super(lang, expr, rootName);
        expression = ExpressionUtils.getObjectCreationExpr(ExpressionReturnValueEvaluator.class, lang, expr, rootName);
    }

    @Override
    public Expression get() {
        return expression;
    }

}

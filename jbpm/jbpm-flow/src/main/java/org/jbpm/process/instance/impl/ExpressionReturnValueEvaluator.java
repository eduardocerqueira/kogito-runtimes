package org.jbpm.process.instance.impl;

import org.kie.kogito.internal.process.runtime.KogitoProcessContext;
import org.kie.kogito.process.expr.Expression;
import org.kie.kogito.process.expr.ExpressionHandlerFactory;

public class ExpressionReturnValueEvaluator implements ReturnValueEvaluator {
    private Expression expression;
    private String rootName;
    private Class<?> returnType;

    public ExpressionReturnValueEvaluator(String lang, String expression, String rootName) {
        this(lang, expression, rootName, Boolean.class);
    }

    public ExpressionReturnValueEvaluator(String lang, String expression, String rootName, Class<?> returnType) {
        this.expression = ExpressionHandlerFactory.get(lang, expression);
        this.rootName = rootName;
        this.returnType = returnType;
    }

    @Override
    public Object evaluate(KogitoProcessContext processContext) throws Exception {
        return expression.eval(processContext.getVariable(rootName), returnType, processContext);
    }
}

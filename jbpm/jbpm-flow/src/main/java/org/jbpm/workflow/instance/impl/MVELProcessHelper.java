package org.jbpm.workflow.instance.impl;

import java.io.Serializable;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

import org.drools.mvel.MVELSafeHelper;
import org.drools.mvel.util.MVELEvaluator;
import org.kie.kogito.internal.RuntimeEnvironment;
import org.mvel2.ErrorDetail;
import org.mvel2.MVEL;
import org.mvel2.ParserContext;
import org.mvel2.compiler.ExpressionCompiler;

public class MVELProcessHelper {

    private static final Supplier<MVELEvaluator> EVALUATOR_SUPPLIER =
            RuntimeEnvironment.isJdk() ? MVELSafeHelper::getEvaluator : () -> {
                throw new UnsupportedOperationException("MVEL evaluation is not supported in native image");
            };

    private static final Function<String, Serializable> EXPR_COMPILER =
            RuntimeEnvironment.isJdk() ? MVEL::compileExpression : expr -> {
                throw new UnsupportedOperationException("MVEL compilation is not supported in native image");
            };

    private static final Function<String, List<ErrorDetail>> EXPR_COMPILER_DETAILED =
            RuntimeEnvironment.isJdk() ? MVELProcessHelper::expressionCompiler : expr -> {
                throw new UnsupportedOperationException("MVEL compilation is not supported in native image");
            };

    private MVELProcessHelper() {

    }

    public static MVELEvaluator evaluator() {
        return EVALUATOR_SUPPLIER.get();
    }

    public static Serializable compileExpression(String expr) {
        return EXPR_COMPILER.apply(expr);
    }

    public static List<ErrorDetail> validateExpression(String expression) {
        return EXPR_COMPILER_DETAILED.apply(expression);
    }

    private static List<ErrorDetail> expressionCompiler(String actionString) {
        ParserContext parserContext = new ParserContext();
        ExpressionCompiler compiler = new ExpressionCompiler(actionString,
                parserContext);
        compiler.setVerifying(true);
        compiler.compile();
        return parserContext.getErrorList();
    }
}

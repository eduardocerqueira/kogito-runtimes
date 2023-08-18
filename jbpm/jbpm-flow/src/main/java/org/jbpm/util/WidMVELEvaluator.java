package org.jbpm.util;

import java.util.HashMap;

import org.drools.util.StringUtils;
import org.mvel2.MVEL;
import org.mvel2.ParserContext;
import org.mvel2.compiler.ExpressionCompiler;

public class WidMVELEvaluator {

    public static ParserContext WID_PARSER_CONTEXT;
    // change this if data types change location
    public static final String DATA_TYPE_PACKAGE = "org.jbpm.process.core.datatype.impl.type";

    static {
        WID_PARSER_CONTEXT = new ParserContext();
        WID_PARSER_CONTEXT.addPackageImport(DATA_TYPE_PACKAGE);
        WID_PARSER_CONTEXT.setRetainParserState(false);
    }

    private WidMVELEvaluator() {

    }

    public static Object eval(final String expression) {
        ExpressionCompiler compiler = new ExpressionCompiler(getRevisedExpression(expression),
                WID_PARSER_CONTEXT);

        return MVEL.executeExpression(compiler.compile(), new HashMap());
    }

    private static String getRevisedExpression(String expression) {
        if (StringUtils.isEmpty(expression)) {
            return expression;
        }
        return expression.replaceAll("import org\\.drools\\.core\\.process\\.core\\.datatype\\.impl\\.type\\.*([^;])*;",
                "").replaceAll("import org\\.jbpm\\.process\\.core\\.datatype\\.impl\\.type\\.*([^;])*;",
                        "");
    }
}

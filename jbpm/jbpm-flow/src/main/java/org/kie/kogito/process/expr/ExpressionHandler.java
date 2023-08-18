package org.kie.kogito.process.expr;

public interface ExpressionHandler {

    Expression get(String expr);

    String lang();
}

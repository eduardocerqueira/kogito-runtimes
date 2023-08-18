package org.kie.kogito.expr.jq;

import java.util.function.Supplier;

import org.kie.kogito.process.expr.Expression;
import org.kie.kogito.serverless.workflow.utils.CachedExpressionHandler;
import org.slf4j.LoggerFactory;

import net.thisptr.jackson.jq.BuiltinFunctionLoader;
import net.thisptr.jackson.jq.Scope;
import net.thisptr.jackson.jq.Versions;

public class JqExpressionHandler extends CachedExpressionHandler {

    private static Supplier<Scope> scopeSupplier = new DefaultScopeSupplier();

    public static void setScopeSupplier(Supplier<Scope> scopeSupplier) {
        JqExpressionHandler.scopeSupplier = scopeSupplier;
    }

    private static class DefaultScopeSupplier implements Supplier<Scope> {
        private static class DefaultScope {
            private static Scope scope;
            static {
                LoggerFactory.getLogger(JqExpressionHandler.class).info("Using default scope");
                scope = Scope.newEmptyScope();
                BuiltinFunctionLoader.getInstance().loadFunctions(Versions.JQ_1_6, scope);
            }
        }

        @Override
        public Scope get() {
            return DefaultScope.scope;
        }
    }

    @Override
    public Expression buildExpression(String expr) {
        return new JqExpression(scopeSupplier, expr, Versions.JQ_1_6);
    }

    @Override
    public String lang() {
        return JqExpression.LANG;
    }
}

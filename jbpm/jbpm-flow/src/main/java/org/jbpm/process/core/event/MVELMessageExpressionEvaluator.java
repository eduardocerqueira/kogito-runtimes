package org.jbpm.process.core.event;

import java.util.function.Function;

import org.jbpm.process.core.correlation.CorrelationExpressionEvaluator;
import org.mvel2.MVEL;
import org.mvel2.integration.VariableResolver;
import org.mvel2.integration.impl.ImmutableDefaultFactory;
import org.mvel2.integration.impl.SimpleValueResolver;

public class MVELMessageExpressionEvaluator implements CorrelationExpressionEvaluator {

    private static final long serialVersionUID = 3516244528735842694L;

    private String expression;

    public MVELMessageExpressionEvaluator(String expression) {
        this.expression = expression;
    }

    @Override
    public Object eval(Object event) {
        return MVEL.eval(expression, event);
    }

    @Override
    public Object eval(Function<String, Object> resolver) {
        ImmutableDefaultFactory factory = new ImmutableDefaultFactory() {
            private static final long serialVersionUID = 1L;

            @Override
            public boolean isResolveable(String name) {
                return resolver.apply("#{" + name + "}") != null;
            };

            @Override
            public VariableResolver getVariableResolver(String name) {
                return new SimpleValueResolver(resolver.apply("#{" + name + "}"));
            };
        };
        return MVEL.eval(expression, factory);
    }

}
package org.jbpm.workflow.core.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Matcher;

import org.jbpm.process.instance.impl.AssignmentAction;
import org.jbpm.process.instance.impl.AssignmentProducer;
import org.jbpm.util.PatternConstants;
import org.jbpm.workflow.instance.impl.MVELProcessHelper;
import org.mvel2.integration.VariableResolver;
import org.mvel2.integration.impl.ImmutableDefaultFactory;
import org.mvel2.integration.impl.SimpleValueResolver;

public class InputExpressionAssignment implements AssignmentAction {

    private DataDefinition from;
    private DataDefinition to;

    public InputExpressionAssignment(DataDefinition from, DataDefinition to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public void execute(Function<String, Object> sourceResolver, Function<String, Object> targetResolver, AssignmentProducer producer) throws Exception {
        // producer in this case is void
        ImmutableDefaultFactory immutableDefaultFactory = new ImmutableDefaultFactory() {

            @Override
            public boolean isResolveable(String name) {
                return sourceResolver.apply(name) != null;
            }

            @Override
            public VariableResolver getVariableResolver(String name) {
                return new SimpleValueResolver(sourceResolver.apply(name));
            }

        };
        producer.accept(to.getLabel(), evalInput(immutableDefaultFactory, from.getExpression()));
    }

    private Object evalInput(ImmutableDefaultFactory factory, String expression) {
        String outcome = expression;
        Matcher matcher = PatternConstants.PARAMETER_MATCHER.matcher(expression);
        Map<String, Object> values = new HashMap<>();
        if (matcher.find()) {
            matcher.reset();
            while (matcher.find()) {
                String paramName = matcher.group(1);
                Object value = MVELProcessHelper.evaluator().eval(paramName, factory);
                if (value != null) {
                    values.put(paramName, value);
                }
            }
        }
        if (values.size() == 1) {
            return values.values().iterator().next();
        }

        for (Map.Entry<String, Object> entry : values.entrySet()) {
            outcome = outcome.replace("#{" + entry.getKey() + "}", entry.getValue().toString());
        }
        return outcome;
    }
}
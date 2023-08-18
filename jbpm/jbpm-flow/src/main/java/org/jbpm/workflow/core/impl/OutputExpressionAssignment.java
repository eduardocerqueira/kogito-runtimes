package org.jbpm.workflow.core.impl;

import java.io.Serializable;
import java.util.function.Function;
import java.util.regex.Matcher;

import org.jbpm.process.instance.impl.AssignmentAction;
import org.jbpm.process.instance.impl.AssignmentProducer;
import org.jbpm.util.PatternConstants;
import org.jbpm.workflow.instance.impl.MVELProcessHelper;
import org.mvel2.integration.VariableResolver;
import org.mvel2.integration.impl.ImmutableDefaultFactory;
import org.mvel2.integration.impl.SimpleValueResolver;

public class OutputExpressionAssignment implements AssignmentAction {

    private DataDefinition from;
    private Serializable toCompiled;
    private DataDefinition to;

    public OutputExpressionAssignment(DataDefinition from, DataDefinition to) {
        this.from = from;
        this.to = to;
        Matcher matcher = PatternConstants.PARAMETER_MATCHER.matcher(this.to.getExpression());
        if (matcher.find()) {
            toCompiled = MVELProcessHelper.compileExpression(matcher.group(1) + " = _value");
        }
    }

    @Override
    public void execute(Function<String, Object> sourceResolver, Function<String, Object> targetResolver, AssignmentProducer producer) throws Exception {
        // producer in this case is void
        Object value = MVELProcessHelper.evaluator().executeExpression(toCompiled, new ImmutableDefaultFactory() {

            @Override
            public boolean isResolveable(String name) {
                return targetResolver.apply(name) != null || name.equals("_value");
            }

            @Override
            public VariableResolver getVariableResolver(String name) {
                if ("_value".equals(name)) {
                    return new SimpleValueResolver(sourceResolver.apply(from.getLabel()));
                } else {
                    return new SimpleValueResolver(targetResolver.apply(name));
                }
            }

        });
        producer.accept(to.getId(), value);
    }

}
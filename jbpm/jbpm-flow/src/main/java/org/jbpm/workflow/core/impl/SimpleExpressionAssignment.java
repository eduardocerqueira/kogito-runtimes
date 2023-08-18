package org.jbpm.workflow.core.impl;

import java.util.function.Function;

import org.jbpm.process.instance.impl.AssignmentAction;
import org.jbpm.process.instance.impl.AssignmentProducer;

public class SimpleExpressionAssignment implements AssignmentAction {

    private DataDefinition from;
    private DataDefinition to;

    public SimpleExpressionAssignment(DataDefinition from, DataDefinition to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public void execute(Function<String, Object> sourceResolver, Function<String, Object> targetResolver, AssignmentProducer producer) throws Exception {
        producer.accept(to.getLabel(), sourceResolver.apply(from.getLabel()));
    }

}
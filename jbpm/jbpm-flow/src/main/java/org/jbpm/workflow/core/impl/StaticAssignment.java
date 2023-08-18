package org.jbpm.workflow.core.impl;

import java.util.function.Function;

import org.jbpm.process.core.datatype.DataType;
import org.jbpm.process.core.datatype.DataTypeResolver;
import org.jbpm.process.instance.impl.AssignmentAction;
import org.jbpm.process.instance.impl.AssignmentProducer;

public class StaticAssignment implements AssignmentAction {

    private String constant;
    private DataDefinition to;

    public StaticAssignment(String constant, DataDefinition to) {
        this.constant = constant;
        this.to = to;
    }

    @Override
    public void execute(Function<String, Object> sourceResolver, Function<String, Object> targetResolver, AssignmentProducer producer) throws Exception {
        // in case it is just a simple string we try to convert to the target type
        DataType dataType = DataTypeResolver.fromType(to.getType(), Thread.currentThread().getContextClassLoader());
        producer.accept(to.getLabel(), dataType.readValue(constant));
    }

}
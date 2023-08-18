package org.jbpm.bpmn2.feel;

import org.jbpm.process.builder.ActionBuilder;
import org.jbpm.process.builder.AssignmentBuilder;
import org.jbpm.process.builder.ProcessBuildContext;
import org.jbpm.process.builder.ProcessClassBuilder;
import org.jbpm.process.builder.ReturnValueEvaluatorBuilder;
import org.jbpm.process.builder.dialect.ProcessDialect;

public class FeelProcessDialect implements ProcessDialect {

    private static final ReturnValueEvaluatorBuilder returnValueEvaluatorBuilder = new FeelReturnValueEvaluatorBuilder();

    public void addProcess(final ProcessBuildContext context) {
        // @TODO setup line mappings
    }

    public ActionBuilder getActionBuilder() {
        throw new UnsupportedOperationException("FeelProcessDialect.getActionBuilder is not supported");
    }

    public ProcessClassBuilder getProcessClassBuilder() {
        throw new UnsupportedOperationException("FeelProcessDialect.getProcessClassBuilder is not supported");
    }

    public ReturnValueEvaluatorBuilder getReturnValueEvaluatorBuilder() {
        return returnValueEvaluatorBuilder;
    }

    public AssignmentBuilder getAssignmentBuilder() {
        throw new UnsupportedOperationException("FEEL assignments not supported");
    }

}

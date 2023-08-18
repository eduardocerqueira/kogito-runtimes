package org.jbpm.bpmn2.xpath;

import org.jbpm.process.builder.ActionBuilder;
import org.jbpm.process.builder.AssignmentBuilder;
import org.jbpm.process.builder.ProcessBuildContext;
import org.jbpm.process.builder.ProcessClassBuilder;
import org.jbpm.process.builder.ReturnValueEvaluatorBuilder;
import org.jbpm.process.builder.dialect.ProcessDialect;

/**
 * Please make sure to use the getter methods when referring to the static final fields,
 * because this class is extended in other modules (jbpm-kie-services).
 */
public class XPATHProcessDialect implements ProcessDialect {

    public static final String ID = "XPath";
    private static final ActionBuilder actionBuilder = new XPATHActionBuilder();
    private static final ReturnValueEvaluatorBuilder returnValueBuilder = new XPATHReturnValueEvaluatorBuilder();
    private static final AssignmentBuilder assignmentBuilder = new XPATHAssignmentBuilder();

    public void addProcess(final ProcessBuildContext context) {
        // @TODO setup line mappings
    }

    public ActionBuilder getActionBuilder() {
        return actionBuilder;
    }

    public ProcessClassBuilder getProcessClassBuilder() {
        throw new UnsupportedOperationException("MVELProcessDialect.getProcessClassBuilder is not supported");
    }

    public ReturnValueEvaluatorBuilder getReturnValueEvaluatorBuilder() {
        return returnValueBuilder;
    }

    public AssignmentBuilder getAssignmentBuilder() {
        return assignmentBuilder;
    }

}

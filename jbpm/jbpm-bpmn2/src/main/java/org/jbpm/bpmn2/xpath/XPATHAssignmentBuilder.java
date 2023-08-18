package org.jbpm.bpmn2.xpath;

import java.util.List;

import org.drools.compiler.rule.builder.PackageBuildContext;
import org.jbpm.process.builder.AssignmentBuilder;
import org.jbpm.workflow.core.impl.DataDefinition;
import org.jbpm.workflow.core.node.Assignment;

public class XPATHAssignmentBuilder implements AssignmentBuilder {

    @Override
    public void build(PackageBuildContext context,
            Assignment assignment,
            List<DataDefinition> sources,
            DataDefinition target) {
        assignment.setMetaData("Action", new XPATHAssignmentAction(assignment, sources, target));
    }
}

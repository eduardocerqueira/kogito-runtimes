package org.jbpm.process.builder;

import java.util.List;

import org.drools.compiler.rule.builder.PackageBuildContext;
import org.jbpm.workflow.core.impl.DataDefinition;
import org.jbpm.workflow.core.node.Assignment;

public interface AssignmentBuilder {

    public void build(final PackageBuildContext context,
            final Assignment assignment,
            final List<DataDefinition> sourceExpr,
            final DataDefinition targetExpr);

}

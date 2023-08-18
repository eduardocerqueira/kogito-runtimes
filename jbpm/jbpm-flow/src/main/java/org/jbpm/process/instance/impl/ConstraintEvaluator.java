package org.jbpm.process.instance.impl;

import org.jbpm.workflow.core.Constraint;
import org.jbpm.workflow.instance.NodeInstance;
import org.kie.api.definition.process.Connection;

public interface ConstraintEvaluator extends Constraint {

    public boolean evaluate(NodeInstance instance,
            Connection connection,
            Constraint constraint);
}

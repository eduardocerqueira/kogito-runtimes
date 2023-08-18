package org.jbpm.process.builder;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.drools.drl.ast.descr.ProcessDescr;
import org.drools.drl.ast.descr.ReturnValueDescr;
import org.jbpm.process.builder.dialect.ProcessDialect;
import org.jbpm.process.builder.dialect.ProcessDialectRegistry;
import org.jbpm.process.instance.impl.ReturnValueConstraintEvaluator;
import org.jbpm.process.instance.impl.RuleConstraintEvaluator;
import org.jbpm.workflow.core.Constraint;
import org.jbpm.workflow.core.impl.ConnectionRef;
import org.jbpm.workflow.core.impl.ConstraintImpl;
import org.jbpm.workflow.core.impl.NodeImpl;
import org.jbpm.workflow.core.node.Split;
import org.kie.api.definition.process.Connection;
import org.kie.api.definition.process.Node;
import org.kie.api.definition.process.Process;

public class MultiConditionalSequenceFlowNodeBuilder implements ProcessNodeBuilder {

    public void build(Process process, ProcessDescr processDescr,
            ProcessBuildContext context, Node node) {

        Map<ConnectionRef, Constraint> constraints = ((NodeImpl) node).getConstraints();

        // exclude split as it is handled with separate builder and nodes with non conditional sequence flows
        if (node instanceof Split || constraints.size() == 0) {
            return;
        }

        // we need to clone the map, so we can update the original while iterating.
        Map<ConnectionRef, Constraint> map = new HashMap<>(constraints);
        for (Iterator<Map.Entry<ConnectionRef, Constraint>> it = map.entrySet().iterator(); it.hasNext();) {
            Map.Entry<ConnectionRef, Constraint> entry = it.next();
            ConnectionRef connection = entry.getKey();
            ConstraintImpl constraint = (ConstraintImpl) entry.getValue();
            Connection outgoingConnection = null;
            for (Connection out : ((NodeImpl) node).getDefaultOutgoingConnections()) {
                if (out.getToType().equals(connection.getToType())
                        && out.getTo().getId() == connection.getNodeId()) {
                    outgoingConnection = out;
                }
            }
            if (outgoingConnection == null) {
                throw new IllegalArgumentException("Could not find outgoing connection");
            }
            if ("rule".equals(constraint.getType())) {
                RuleConstraintEvaluator ruleConstraint = new RuleConstraintEvaluator();
                ruleConstraint.setDialect(constraint.getDialect());
                ruleConstraint.setName(constraint.getName());
                ruleConstraint.setPriority(constraint.getPriority());
                ruleConstraint.setDefault(constraint.isDefault());
                ((NodeImpl) node).setConstraint(outgoingConnection, ruleConstraint);
            } else if ("code".equals(constraint.getType())) {
                ReturnValueConstraintEvaluator returnValueConstraint = new ReturnValueConstraintEvaluator();
                returnValueConstraint.setDialect(constraint.getDialect());
                returnValueConstraint.setName(constraint.getName());
                returnValueConstraint.setPriority(constraint.getPriority());
                returnValueConstraint.setDefault(constraint.isDefault());
                ((NodeImpl) node).setConstraint(outgoingConnection, returnValueConstraint);

                ReturnValueDescr returnValueDescr = new ReturnValueDescr();
                returnValueDescr.setText(constraint.getConstraint());
                returnValueDescr.setResource(processDescr.getResource());

                ProcessDialect dialect = ProcessDialectRegistry.getDialect(constraint.getDialect());
                dialect.getReturnValueEvaluatorBuilder().build(context, returnValueConstraint, returnValueDescr, (NodeImpl) node);
            }
        }

    }

}

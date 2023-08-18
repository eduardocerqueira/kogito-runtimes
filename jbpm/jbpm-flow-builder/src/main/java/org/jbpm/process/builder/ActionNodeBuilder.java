package org.jbpm.process.builder;

import java.util.HashMap;
import java.util.Map;

import org.drools.drl.ast.descr.ActionDescr;
import org.drools.drl.ast.descr.ProcessDescr;
import org.jbpm.process.builder.dialect.ProcessDialect;
import org.jbpm.process.builder.dialect.ProcessDialectRegistry;
import org.jbpm.workflow.core.WorkflowProcess;
import org.jbpm.workflow.core.impl.DroolsConsequenceAction;
import org.jbpm.workflow.core.node.ActionNode;
import org.kie.api.definition.process.Node;
import org.kie.api.definition.process.Process;

public class ActionNodeBuilder extends ExtendedNodeBuilder {

    @Override
    public void build(Process process,
            ProcessDescr processDescr,
            ProcessBuildContext context,
            Node node) {
        super.build(process, processDescr, context, node);
        ActionNode actionNode = (ActionNode) node;
        DroolsConsequenceAction action = (DroolsConsequenceAction) actionNode.getAction();
        ActionDescr actionDescr = new ActionDescr();
        actionDescr.setText(action.getConsequence());
        actionDescr.setResource(processDescr.getResource());

        ProcessDialect dialect = ProcessDialectRegistry.getDialect(action.getDialect());
        dialect.getActionBuilder().build(context, action, actionDescr, actionNode);

        WorkflowProcess wfProcess = (WorkflowProcess) process;
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("imports", wfProcess.getImports());
        parameters.put("classloader", context.getConfiguration().getClassLoader());

        buildDataAssociation(context, ((ActionNode) node).getInAssociations(), parameters);
        buildDataAssociation(context, ((ActionNode) node).getOutAssociations(), parameters);
    }

}

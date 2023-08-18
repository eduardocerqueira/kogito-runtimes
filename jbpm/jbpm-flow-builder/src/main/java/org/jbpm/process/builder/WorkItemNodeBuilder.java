package org.jbpm.process.builder;

import java.util.HashMap;
import java.util.Map;

import org.drools.drl.ast.descr.ProcessDescr;
import org.jbpm.workflow.core.WorkflowProcess;
import org.jbpm.workflow.core.node.WorkItemNode;
import org.kie.api.definition.process.Node;
import org.kie.api.definition.process.Process;

public class WorkItemNodeBuilder extends EventBasedNodeBuilder {

    @Override
    public void build(Process process, ProcessDescr processDescr,
            ProcessBuildContext context, Node node) {
        super.build(process, processDescr, context, node);
        WorkflowProcess wfProcess = (WorkflowProcess) process;
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("imports", wfProcess.getImports());
        parameters.put("classloader", context.getConfiguration().getClassLoader());

        buildDataAssociation(context, ((WorkItemNode) node).getInAssociations(), parameters);
        buildDataAssociation(context, ((WorkItemNode) node).getOutAssociations(), parameters);
    }

}

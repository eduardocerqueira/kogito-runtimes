package org.jbpm.process.builder;

import java.util.Map;

import org.drools.drl.ast.descr.ProcessDescr;
import org.jbpm.process.core.timer.Timer;
import org.jbpm.workflow.core.DroolsAction;
import org.jbpm.workflow.core.impl.NodeImpl;
import org.jbpm.workflow.core.node.StateBasedNode;
import org.kie.api.definition.process.Node;
import org.kie.api.definition.process.Process;

public class EventBasedNodeBuilder extends ExtendedNodeBuilder {

    @Override
    public void build(Process process,
            ProcessDescr processDescr,
            ProcessBuildContext context,
            Node node) {
        super.build(process, processDescr, context, node);
        Map<Timer, DroolsAction> timers = ((StateBasedNode) node).getTimers();
        if (timers != null) {
            for (DroolsAction action : timers.values()) {
                buildAction(action, context, (NodeImpl) node);
            }
        }
    }

}

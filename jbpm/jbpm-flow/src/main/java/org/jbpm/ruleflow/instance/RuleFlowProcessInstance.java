package org.jbpm.ruleflow.instance;

import java.util.List;

import org.jbpm.ruleflow.core.RuleFlowProcess;
import org.jbpm.workflow.core.node.StartNode;
import org.jbpm.workflow.instance.impl.WorkflowProcessInstanceImpl;
import org.kie.api.definition.process.Node;

public class RuleFlowProcessInstance extends WorkflowProcessInstanceImpl {

    private static final long serialVersionUID = 510l;

    public RuleFlowProcess getRuleFlowProcess() {
        return (RuleFlowProcess) getProcess();
    }

    public void internalStart(String trigger) {
        StartNode startNode = getRuleFlowProcess().getStart(trigger, varName -> getVariable(varName));
        if (startNode != null) {
            getNodeInstance(startNode).trigger(null, null);
        } else if (!getRuleFlowProcess().isDynamic()) {
            throw new IllegalArgumentException("There is no start node that matches the trigger " + (trigger == null ? "none" : trigger));
        }

        // activate ad hoc fragments if they are marked as such
        List<Node> autoStartNodes = getRuleFlowProcess().getAutoStartNodes();
        autoStartNodes.forEach(autoStartNode -> signalEvent(autoStartNode.getName(), null));
    }
}

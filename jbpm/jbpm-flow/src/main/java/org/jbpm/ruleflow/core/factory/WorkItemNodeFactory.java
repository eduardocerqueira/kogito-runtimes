package org.jbpm.ruleflow.core.factory;

import org.jbpm.ruleflow.core.RuleFlowNodeContainerFactory;
import org.jbpm.workflow.core.NodeContainer;
import org.jbpm.workflow.core.node.WorkItemNode;

public class WorkItemNodeFactory<T extends RuleFlowNodeContainerFactory<T, ?>> extends AbstractWorkItemNodeFactory<WorkItemNodeFactory<T>, T> {

    public static final String METHOD_WORK_NAME = "workName";
    public static final String METHOD_WORK_PARAMETER = "workParameter";

    public WorkItemNodeFactory(T nodeContainerFactory, NodeContainer nodeContainer, long id) {
        super(nodeContainerFactory, nodeContainer, new WorkItemNode(), id);
    }
}

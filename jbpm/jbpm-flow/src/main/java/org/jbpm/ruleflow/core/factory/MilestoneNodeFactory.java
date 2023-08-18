package org.jbpm.ruleflow.core.factory;

import java.util.function.Predicate;

import org.jbpm.ruleflow.core.RuleFlowNodeContainerFactory;
import org.jbpm.workflow.core.NodeContainer;
import org.jbpm.workflow.core.node.MilestoneNode;
import org.kie.api.runtime.process.ProcessContext;

public class MilestoneNodeFactory<T extends RuleFlowNodeContainerFactory<T, ?>> extends StateBasedNodeFactory<MilestoneNodeFactory<T>, T> {

    public static final String METHOD_CONDITION = "condition";

    public MilestoneNodeFactory(T nodeContainerFactory, NodeContainer nodeContainer, long id) {
        super(nodeContainerFactory, nodeContainer, new MilestoneNode(), id);
    }

    protected MilestoneNode getMilestoneNode() {
        return (MilestoneNode) getNode();
    }

    public MilestoneNodeFactory<T> condition(Predicate<ProcessContext> condition) {
        getMilestoneNode().setCondition(condition);
        return this;
    }
}

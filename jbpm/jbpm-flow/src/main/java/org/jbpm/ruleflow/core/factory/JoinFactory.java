package org.jbpm.ruleflow.core.factory;

import org.jbpm.ruleflow.core.RuleFlowNodeContainerFactory;
import org.jbpm.workflow.core.NodeContainer;
import org.jbpm.workflow.core.node.Join;

public class JoinFactory<T extends RuleFlowNodeContainerFactory<T, ?>> extends NodeFactory<JoinFactory<T>, T> {

    public static final String METHOD_TYPE = "type";

    public JoinFactory(T nodeContainerFactory, NodeContainer nodeContainer, long id) {
        super(nodeContainerFactory, nodeContainer, new Join(), id);
    }

    public Join getJoin() {
        return (Join) getNode();
    }

    @Override
    public JoinFactory<T> name(String name) {
        super.name(name);
        return this;
    }

    public JoinFactory<T> type(int type) {
        getJoin().setType(type);
        return this;
    }

    public JoinFactory<T> type(String n) {
        getJoin().setN(n);
        return this;
    }

}

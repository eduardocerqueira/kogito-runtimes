package org.jbpm.ruleflow.core.factory;

import org.jbpm.process.instance.impl.Action;
import org.jbpm.ruleflow.core.RuleFlowNodeContainerFactory;

public interface SupportsAction<T extends NodeFactory<T, P>, P extends RuleFlowNodeContainerFactory<P, ?>> {
    T action(Action action);
}

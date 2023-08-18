package org.jbpm.workflow.instance.rule;

import java.util.Optional;
import java.util.ServiceLoader;

import org.jbpm.workflow.instance.node.RuleSetNodeInstance;

public class RuleFlowGroupRuleType extends AbstractRuleType {

    static Optional<RuleFlowGroupRuleTypeEngine> ruleTypeEngineProvider;

    static {
        ServiceLoader<RuleFlowGroupRuleTypeEngine> providers = ServiceLoader.load(RuleFlowGroupRuleTypeEngine.class);
        ruleTypeEngineProvider = providers.findFirst();
    }

    protected RuleFlowGroupRuleType(String name) {
        super(name);
    }

    @Override
    public boolean isRuleFlowGroup() {
        return true;
    }

    @Override
    public void evaluate(RuleSetNodeInstance rsni) {
        ruleTypeEngineProvider.orElseThrow(() -> new IllegalArgumentException("Engine not found for executing RuleFlow rules")).evaluate(rsni, getName());
    }

    @Override
    public String toString() {
        return "RuleFlowGroupRuleType{" +
                "name='" + name + '\'' +
                '}';
    }

}

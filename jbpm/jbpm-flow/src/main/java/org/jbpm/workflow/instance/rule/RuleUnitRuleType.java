package org.jbpm.workflow.instance.rule;

import java.util.Optional;
import java.util.ServiceLoader;

import org.jbpm.workflow.instance.node.RuleSetNodeInstance;

public class RuleUnitRuleType extends AbstractRuleType {

    static Optional<RuleUnitRuleTypeEngine> ruleTypeEngineProvider;

    static {
        ServiceLoader<RuleUnitRuleTypeEngine> providers = ServiceLoader.load(RuleUnitRuleTypeEngine.class);
        ruleTypeEngineProvider = providers.findFirst();
    }

    protected RuleUnitRuleType(String name) {
        super(name);
    }

    @Override
    public void evaluate(RuleSetNodeInstance rsni) {
        ruleTypeEngineProvider.orElseThrow(() -> new IllegalArgumentException("Engine not found for executing RuleUnit rules")).evaluate(rsni);
    }

    @Override
    public boolean isRuleUnit() {
        return true;
    }

    @Override
    public String toString() {
        return "RuleUnitRuleType{" +
                "name='" + name + '\'' +
                '}';
    }
}

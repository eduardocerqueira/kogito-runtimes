package org.jbpm.workflow.instance.rule;

import org.jbpm.workflow.instance.node.RuleSetNodeInstance;

public interface RuleType {

    String UNIT_RULEFLOW_PREFIX = "unit:";
    String DRL_LANG = "http://www.jboss.org/drools/rule";
    String RULE_UNIT_LANG = "http://www.jboss.org/drools/rule-unit";
    String DMN_LANG = "http://www.jboss.org/drools/dmn";

    String getName();

    void evaluate(RuleSetNodeInstance rsni);

    boolean isRuleFlowGroup();

    boolean isRuleUnit();

    boolean isDecision();

    static RuleType of(String name, String language) {
        if (DRL_LANG.equals(language)) {
            return parseRuleFlowGroup(name);
        } else if (RULE_UNIT_LANG.equals(language)) {
            return ruleUnit(name);
        } else {
            throw new IllegalArgumentException("Unsupported language " + language);
        }
    }

    private static RuleType parseRuleFlowGroup(String name) {
        if (name.startsWith(UNIT_RULEFLOW_PREFIX)) {
            String unitId = name.substring(UNIT_RULEFLOW_PREFIX.length());
            return ruleUnit(unitId);
        }
        return ruleFlowGroup(name);
    }

    static RuleFlowGroupRuleType ruleFlowGroup(String name) {
        return new RuleFlowGroupRuleType(name);
    }

    static RuleUnitRuleType ruleUnit(String name) {
        return new RuleUnitRuleType(name);
    }

    static DecisionRuleType decision(String namespace, String model, String decision) {
        return new DecisionRuleType(namespace, model, decision);
    }

}

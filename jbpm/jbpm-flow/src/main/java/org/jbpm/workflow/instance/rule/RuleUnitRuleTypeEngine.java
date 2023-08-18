package org.jbpm.workflow.instance.rule;

import org.jbpm.workflow.instance.node.RuleSetNodeInstance;

public interface RuleUnitRuleTypeEngine {

    void evaluate(RuleSetNodeInstance rsni);

}

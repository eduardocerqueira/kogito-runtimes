package org.jbpm.workflow.instance.rule;

import org.jbpm.workflow.instance.node.RuleSetNodeInstance;

public interface RuleFlowGroupRuleTypeEngine extends AbstractRuleTypeEngine {

    void evaluate(RuleSetNodeInstance rsni, String ruleFlowGroup);

}

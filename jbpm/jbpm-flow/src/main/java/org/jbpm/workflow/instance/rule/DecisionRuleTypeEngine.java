package org.jbpm.workflow.instance.rule;

import org.jbpm.workflow.instance.node.RuleSetNodeInstance;

public interface DecisionRuleTypeEngine extends AbstractRuleTypeEngine {

    void evaluate(RuleSetNodeInstance rsni, String namespace, String model, String decision);

}

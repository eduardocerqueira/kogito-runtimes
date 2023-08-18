package org.jbpm.bpmn2.rule;

import org.drools.core.process.AbstractProcessContext;
import org.drools.ruleunits.api.RuleUnitData;
import org.drools.ruleunits.api.RuleUnitInstance;
import org.jbpm.util.ContextFactory;
import org.jbpm.workflow.core.node.RuleUnitFactory;
import org.jbpm.workflow.instance.node.RuleSetNodeInstance;
import org.jbpm.workflow.instance.rule.RuleUnitRuleTypeEngine;

public class RuleUnitRuleTypeEngineImpl implements RuleUnitRuleTypeEngine {

    public void evaluate(RuleSetNodeInstance rsni) {
        RuleUnitFactory<RuleUnitData> factory = rsni.getRuleSetNode().getRuleUnitFactory();
        AbstractProcessContext context = ContextFactory.fromNode(rsni);
        RuleUnitData model = factory.bind(context);
        try (RuleUnitInstance<RuleUnitData> instance = factory.unit().createInstance(model)) {
            instance.fire();
            factory.unbind(context, model);
            rsni.triggerCompleted();
        }
    }

}

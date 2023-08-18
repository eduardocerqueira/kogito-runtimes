package org.jbpm.workflow.instance.rule;

import java.io.Serializable;

public abstract class AbstractRuleType implements Serializable, RuleType {

    @Override
    public boolean isRuleFlowGroup() {
        return false;
    }

    @Override
    public boolean isDecision() {
        return false;
    }

    @Override
    public boolean isRuleUnit() {
        return false;
    }

    protected String name;

    protected AbstractRuleType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}

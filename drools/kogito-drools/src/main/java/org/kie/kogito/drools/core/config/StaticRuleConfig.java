package org.kie.kogito.drools.core.config;

import org.kie.kogito.rules.RuleConfig;
import org.kie.kogito.rules.RuleEventListenerConfig;

public class StaticRuleConfig implements RuleConfig {

    private final RuleEventListenerConfig ruleEventListenerConfig;

    public StaticRuleConfig(RuleEventListenerConfig ruleEventListenerConfig) {
        this.ruleEventListenerConfig = ruleEventListenerConfig;
    }

    public StaticRuleConfig() {
        this(new DefaultRuleEventListenerConfig());
    }

    @Override
    public RuleEventListenerConfig ruleEventListeners() {
        return ruleEventListenerConfig;
    }
}

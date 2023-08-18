package org.kie.kogito.drools.core.config;

import org.kie.api.event.rule.AgendaEventListener;
import org.kie.api.event.rule.RuleRuntimeEventListener;

public class DefaultRuleEventListenerConfig extends CachedRuleEventListenerConfig {

    public DefaultRuleEventListenerConfig() {
    }

    public DefaultRuleEventListenerConfig(AgendaEventListener... listeners) {
        for (AgendaEventListener listener : listeners) {
            register(listener);
        }
    }

    public DefaultRuleEventListenerConfig(RuleRuntimeEventListener... listeners) {
        for (RuleRuntimeEventListener listener : listeners) {
            register(listener);
        }
    }

}

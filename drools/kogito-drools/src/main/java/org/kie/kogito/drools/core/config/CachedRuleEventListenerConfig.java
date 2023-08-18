package org.kie.kogito.drools.core.config;

import java.util.ArrayList;
import java.util.List;

import org.kie.api.event.rule.AgendaEventListener;
import org.kie.api.event.rule.RuleRuntimeEventListener;
import org.kie.kogito.rules.RuleEventListenerConfig;

public class CachedRuleEventListenerConfig implements RuleEventListenerConfig {

    private final List<AgendaEventListener> agendaListeners;
    private final List<RuleRuntimeEventListener> ruleRuntimeListeners;

    public CachedRuleEventListenerConfig() {
        agendaListeners = new ArrayList<>();
        ruleRuntimeListeners = new ArrayList<>();
    }

    public CachedRuleEventListenerConfig(List<AgendaEventListener> agendaListeners, List<RuleRuntimeEventListener> ruleRuntimeListeners) {
        this.agendaListeners = agendaListeners;
        this.ruleRuntimeListeners = ruleRuntimeListeners;
    }

    public CachedRuleEventListenerConfig register(AgendaEventListener listener) {
        agendaListeners.add(listener);
        return this;
    }

    public CachedRuleEventListenerConfig register(RuleRuntimeEventListener listener) {
        ruleRuntimeListeners.add(listener);
        return this;
    }

    @Override
    public List<AgendaEventListener> agendaListeners() {
        return agendaListeners;
    }

    @Override
    public List<RuleRuntimeEventListener> ruleRuntimeListeners() {
        return ruleRuntimeListeners;
    }

}

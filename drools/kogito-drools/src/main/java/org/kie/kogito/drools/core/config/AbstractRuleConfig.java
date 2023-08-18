package org.kie.kogito.drools.core.config;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.kie.api.event.rule.AgendaEventListener;
import org.kie.api.event.rule.RuleRuntimeEventListener;
import org.kie.kogito.rules.RuleConfig;
import org.kie.kogito.rules.RuleEventListenerConfig;

public abstract class AbstractRuleConfig implements RuleConfig {

    private final RuleEventListenerConfig ruleEventListenerConfig;

    public AbstractRuleConfig(RuleEventListenerConfig ruleEventListenerConfig) {
        this.ruleEventListenerConfig = ruleEventListenerConfig;
    }

    public AbstractRuleConfig(
            Iterable<? extends RuleEventListenerConfig> ruleEventListenerConfigs,
            Iterable<? extends AgendaEventListener> agendaEventListeners,
            Iterable<? extends RuleRuntimeEventListener> ruleRuntimeEventListeners) {
        this.ruleEventListenerConfig = extractRuleEventListenerConfig(
                ruleEventListenerConfigs, agendaEventListeners, ruleRuntimeEventListeners);
    }

    @Override
    public RuleEventListenerConfig ruleEventListeners() {
        return ruleEventListenerConfig;
    }

    private RuleEventListenerConfig extractRuleEventListenerConfig(
            Iterable<? extends RuleEventListenerConfig> ruleEventListenerConfigs,
            Iterable<? extends AgendaEventListener> agendaEventListeners,
            Iterable<? extends RuleRuntimeEventListener> ruleRuntimeEventListeners) {
        return this.mergeRuleEventListenerConfig(
                StreamSupport.stream(ruleEventListenerConfigs.spliterator(), false)
                        .collect(Collectors.toList()),
                StreamSupport.stream(agendaEventListeners.spliterator(), false)
                        .collect(Collectors.toList()),
                StreamSupport.stream(ruleRuntimeEventListeners.spliterator(), false)
                        .collect(Collectors.toList()));
    }

    private RuleEventListenerConfig mergeRuleEventListenerConfig(
            Collection<RuleEventListenerConfig> ruleEventListenerConfigs,
            Collection<AgendaEventListener> agendaEventListeners,
            Collection<RuleRuntimeEventListener> ruleRuntimeEventListeners) {
        return new CachedRuleEventListenerConfig(
                merge(ruleEventListenerConfigs,
                        RuleEventListenerConfig::agendaListeners,
                        agendaEventListeners),
                merge(ruleEventListenerConfigs,
                        RuleEventListenerConfig::ruleRuntimeListeners,
                        ruleRuntimeEventListeners));
    }

    private static <C, L> List<L> merge(Collection<C> configs, Function<C, Collection<L>> configToListeners, Collection<L> listeners) {
        return Stream.concat(
                configs.stream().flatMap(c -> configToListeners.apply(c).stream()), listeners.stream())
                .collect(Collectors.toList());
    }
}

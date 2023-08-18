package org.kie.kogito.rules;

import java.util.List;

import org.kie.api.event.rule.AgendaEventListener;
import org.kie.api.event.rule.RuleRuntimeEventListener;

public interface RuleEventListenerConfig {
    List<AgendaEventListener> agendaListeners();

    List<RuleRuntimeEventListener> ruleRuntimeListeners();
}

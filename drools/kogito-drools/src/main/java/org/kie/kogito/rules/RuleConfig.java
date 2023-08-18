package org.kie.kogito.rules;

import org.kie.kogito.KogitoConfig;

public interface RuleConfig extends KogitoConfig {

    RuleEventListenerConfig ruleEventListeners();
}

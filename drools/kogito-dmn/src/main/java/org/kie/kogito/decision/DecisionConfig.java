package org.kie.kogito.decision;

import org.kie.kogito.KogitoConfig;

public interface DecisionConfig extends KogitoConfig {

    DecisionEventListenerConfig decisionEventListeners();

}

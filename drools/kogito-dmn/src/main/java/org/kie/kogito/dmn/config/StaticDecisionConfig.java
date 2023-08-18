package org.kie.kogito.dmn.config;

import org.kie.kogito.decision.DecisionConfig;
import org.kie.kogito.decision.DecisionEventListenerConfig;

public class StaticDecisionConfig implements DecisionConfig {

    private final DecisionEventListenerConfig decisionEventListenerConfig;

    public StaticDecisionConfig(DecisionEventListenerConfig decisionEventListenerConfig) {
        this.decisionEventListenerConfig = decisionEventListenerConfig;
    }

    public StaticDecisionConfig() {
        this(new DefaultDecisionEventListenerConfig());
    }

    @Override
    public DecisionEventListenerConfig decisionEventListeners() {
        return decisionEventListenerConfig;
    }

}

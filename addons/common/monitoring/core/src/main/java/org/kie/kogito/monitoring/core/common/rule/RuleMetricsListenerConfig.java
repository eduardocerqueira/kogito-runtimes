package org.kie.kogito.monitoring.core.common.rule;

import org.kie.kogito.KogitoGAV;
import org.kie.kogito.drools.core.config.DefaultRuleEventListenerConfig;

import io.micrometer.core.instrument.MeterRegistry;

public class RuleMetricsListenerConfig extends DefaultRuleEventListenerConfig {

    public RuleMetricsListenerConfig(KogitoGAV gav, MeterRegistry meterRegistry) {
        super(new RuleMetricsListener("default-rule-monitoring-listener", gav, meterRegistry));
    }
}

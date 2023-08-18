package org.kie.kogito.monitoring.core.springboot;

import org.kie.kogito.KogitoGAV;
import org.kie.kogito.config.ConfigBean;
import org.kie.kogito.drools.core.config.DefaultRuleEventListenerConfig;
import org.kie.kogito.internal.process.event.KogitoProcessEventListener;
import org.kie.kogito.monitoring.core.common.Constants;
import org.kie.kogito.monitoring.core.common.process.MetricsProcessEventListener;
import org.kie.kogito.monitoring.core.common.rule.RuleMetricsListenerConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.micrometer.core.instrument.Metrics;

@Configuration
public class SpringbootEventListenerFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpringbootEventListenerFactory.class);

    ConfigBean configBean;

    @Autowired
    public SpringbootEventListenerFactory(ConfigBean configBean) {
        this.configBean = configBean;
    }

    @Bean
    @ConditionalOnProperty(
            value = Constants.MONITORING_PROCESS_USE_DEFAULT,
            havingValue = "true",
            matchIfMissing = true)
    public KogitoProcessEventListener produceProcessListener() {
        LOGGER.debug("Producing default listener for process monitoring.");
        return new MetricsProcessEventListener("default-process-monitoring-listener",
                configBean.getGav().orElse(KogitoGAV.EMPTY_GAV),
                Metrics.globalRegistry);
    }

    @ConditionalOnProperty(
            value = Constants.MONITORING_RULE_USE_DEFAULT,
            havingValue = "true",
            matchIfMissing = true)
    @Bean
    public DefaultRuleEventListenerConfig produceRuleListener() {
        LOGGER.debug("Producing default listener for rule monitoring.");
        return new RuleMetricsListenerConfig(configBean.getGav().orElse(KogitoGAV.EMPTY_GAV), Metrics.globalRegistry);
    }
}

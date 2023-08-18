package org.kie.kogito.monitoring.core.quarkus;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import org.kie.kogito.KogitoGAV;
import org.kie.kogito.config.ConfigBean;
import org.kie.kogito.drools.core.config.DefaultRuleEventListenerConfig;
import org.kie.kogito.internal.process.event.KogitoProcessEventListener;
import org.kie.kogito.monitoring.core.common.Constants;
import org.kie.kogito.monitoring.core.common.process.MetricsProcessEventListener;
import org.kie.kogito.monitoring.core.common.rule.RuleMetricsListenerConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.micrometer.core.instrument.Metrics;
import io.quarkus.arc.properties.IfBuildProperty;

@Dependent
public class QuarkusEventListenerFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(QuarkusEventListenerFactory.class);

    ConfigBean configBean;

    @Inject
    public QuarkusEventListenerFactory(ConfigBean configBean) {
        this.configBean = configBean;
    }

    @Produces
    @IfBuildProperty(name = Constants.MONITORING_RULE_USE_DEFAULT, stringValue = "true", enableIfMissing = true)
    public DefaultRuleEventListenerConfig produceRuleListener() {
        LOGGER.debug("Producing default listener for rule monitoring.");
        return new RuleMetricsListenerConfig(configBean.getGav().orElse(KogitoGAV.EMPTY_GAV), Metrics.globalRegistry);
    }

    @Produces
    @IfBuildProperty(name = Constants.MONITORING_PROCESS_USE_DEFAULT, stringValue = "true", enableIfMissing = true)
    public KogitoProcessEventListener produceProcessListener() {
        LOGGER.debug("Producing default listener for process monitoring.");
        return new MetricsProcessEventListener("default-process-monitoring-listener",
                configBean.getGav().orElse(KogitoGAV.EMPTY_GAV), Metrics.globalRegistry);
    }
}

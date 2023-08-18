package org.kie.kogito.monitoring.core.springboot;

import javax.annotation.PostConstruct;

import org.kie.kogito.KogitoGAV;
import org.kie.kogito.config.ConfigBean;
import org.kie.kogito.monitoring.core.common.system.metrics.SystemMetricsCollector;
import org.kie.kogito.monitoring.core.common.system.metrics.SystemMetricsCollectorProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.micrometer.core.instrument.Metrics;

@Component
public class SpringbootSystemMetricsCollectorProvider implements SystemMetricsCollectorProvider {

    @Autowired
    ConfigBean configBean;

    SystemMetricsCollector systemMetricsCollector;

    @PostConstruct
    public void init() {
        systemMetricsCollector = new SystemMetricsCollector(configBean.getGav().orElse(KogitoGAV.EMPTY_GAV),
                Metrics.globalRegistry);
    }

    @Override
    public SystemMetricsCollector get() {
        return systemMetricsCollector;
    }
}

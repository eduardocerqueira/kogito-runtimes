package org.kie.kogito.monitoring.core.quarkus;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Singleton;

import org.kie.kogito.KogitoGAV;
import org.kie.kogito.config.ConfigBean;
import org.kie.kogito.monitoring.core.common.system.metrics.SystemMetricsCollector;
import org.kie.kogito.monitoring.core.common.system.metrics.SystemMetricsCollectorProvider;

import io.micrometer.core.instrument.Metrics;
import io.quarkus.runtime.Startup;

@Singleton
@Startup
public class QuarkusSystemMetricsCollectorProvider implements SystemMetricsCollectorProvider {

    @Inject
    ConfigBean configBean;

    SystemMetricsCollector systemMetricsCollector;

    @PostConstruct
    public void init() {
        systemMetricsCollector = new SystemMetricsCollector(configBean.getGav().orElse(KogitoGAV.EMPTY_GAV), Metrics.globalRegistry);
    }

    public SystemMetricsCollector get() {
        return systemMetricsCollector;
    }
}

package org.kie.kogito.monitoring.elastic.common;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.micrometer.core.instrument.Metrics;
import io.micrometer.core.instrument.composite.CompositeMeterRegistry;
import io.micrometer.elastic.ElasticConfig;
import io.micrometer.elastic.ElasticMeterRegistry;

public class ElasticRegistry {

    private static final Logger logger = LoggerFactory.getLogger(ElasticRegistry.class);
    private ElasticMeterRegistry registry;
    protected CompositeMeterRegistry compositeMeterRegistry = Metrics.globalRegistry;

    protected ElasticRegistry() {
    }

    protected void start(ElasticConfig elasticConfig) {
        start(elasticConfig, Executors.defaultThreadFactory());
    }

    protected void start(ElasticConfig elasticConfig, ThreadFactory threadFactory) {
        registry = ElasticMeterRegistry.builder(elasticConfig).build();
        compositeMeterRegistry.add(registry);
        registry.start(threadFactory);
        logger.debug("Micrometer Elastic publisher started.");
    }
}

package org.kie.kogito.monitoring.elastic.common;

import java.util.Map;

import io.micrometer.elastic.ElasticConfig;

public class ElasticConfigFactory {

    private final KogitoElasticConfig kogitoElasticConfig;

    public ElasticConfigFactory() {
        this.kogitoElasticConfig = new KogitoElasticConfig();
    }

    public ElasticConfigFactory withProperty(String key, String value) {
        this.kogitoElasticConfig.withProperty(key, value);
        return this;
    }

    public ElasticConfig getElasticConfig() {
        Map<String, String> configMap = kogitoElasticConfig.getConfigMap();
        return configMap::get;
    }
}

package org.kie.kogito.monitoring.elastic.common;

import java.util.HashMap;
import java.util.Map;

import io.micrometer.elastic.ElasticConfig;

public class KogitoElasticConfig {

    public static final String HOST_KEY = "host";
    public static final String INDEX_KEY = "index";
    public static final String STEP_KEY = "step";
    public static final String INDEX_DATE_FORMAT_KEY = "indexDateFormat";
    public static final String TIMESTAMP_FIELD_NAME_KEY = "timestampFieldName";
    public static final String AUTO_CREATE_INDEX_KEY = "autoCreateIndex";
    public static final String USERNAME_KEY = "userName";
    public static final String PASSWORD_KEY = "password";
    public static final String PIPELINE_KEY = "pipeline";
    public static final String INDEX_DATE_SEPARATOR_KEY = "indexDateSeparator";
    public static final String DOCUMENT_TYPE_KEY = "documentType";

    private final String prefix = ElasticConfig.DEFAULT.prefix();
    private final Map<String, String> configMap;

    public KogitoElasticConfig() {
        this.configMap = new HashMap<>();
    }

    public KogitoElasticConfig withProperty(String key, String value) {
        configMap.put(String.join(".", prefix, key), value);
        return this;
    }

    public Map<String, String> getConfigMap() {
        return configMap;
    }
}

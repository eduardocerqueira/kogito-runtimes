package org.kie.kogito.monitoring.elastic.common;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class KogitoElasticConfigTest {

    @Test
    public void testConfigMapPrefix() {
        KogitoElasticConfig kogitoElasticConfig = new KogitoElasticConfig();
        kogitoElasticConfig.withProperty("key", "value");
        assertThat(kogitoElasticConfig.getConfigMap()).containsEntry("elastic.key", "value");
    }
}
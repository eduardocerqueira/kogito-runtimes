package org.kie.kogito.monitoring.elastic.common;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;

import io.micrometer.elastic.ElasticConfig;

import static org.assertj.core.api.Assertions.assertThat;

public class ElasticRegistryTest {

    @Test
    public void testElasticMicrometerIsUsingOurProperties() throws InterruptedException {
        ElasticRegistry elasticRegistry = new ElasticRegistry();

        KogitoElasticConfig kogitoElasticConfig = new KogitoElasticConfig();
        kogitoElasticConfig.withProperty(KogitoElasticConfig.HOST_KEY, "http://mylocalhost:8080");
        kogitoElasticConfig.withProperty(KogitoElasticConfig.INDEX_KEY, "myIndex");
        kogitoElasticConfig.withProperty(KogitoElasticConfig.STEP_KEY, "1s");
        kogitoElasticConfig.withProperty(KogitoElasticConfig.TIMESTAMP_FIELD_NAME_KEY, "myTimestampName");
        kogitoElasticConfig.withProperty(KogitoElasticConfig.USERNAME_KEY, "pippo");
        kogitoElasticConfig.withProperty(KogitoElasticConfig.PASSWORD_KEY, "pluto");
        kogitoElasticConfig.withProperty(KogitoElasticConfig.PIPELINE_KEY, "mypipe");
        kogitoElasticConfig.withProperty(KogitoElasticConfig.INDEX_DATE_SEPARATOR_KEY, "/");
        kogitoElasticConfig.withProperty(KogitoElasticConfig.DOCUMENT_TYPE_KEY, "doc");

        Map<String, String> configMap = kogitoElasticConfig.getConfigMap();
        Map<String, CountDownLatch> countDownLatchMap = new HashMap<>();
        configMap.keySet().forEach(x -> countDownLatchMap.put(x, new CountDownLatch(1)));

        ElasticConfig elasticConfig = s -> {
            countDownLatchMap.computeIfPresent(s, (k, v) -> {
                v.countDown();
                return v;
            });
            return configMap.getOrDefault(s, null);
        };

        elasticRegistry.start(elasticConfig);

        for (CountDownLatch value : countDownLatchMap.values()) {
            assertThat(value.await(20, TimeUnit.SECONDS)).isTrue();
        }
    }
}

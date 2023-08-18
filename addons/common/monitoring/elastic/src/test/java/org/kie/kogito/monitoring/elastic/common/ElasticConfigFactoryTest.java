package org.kie.kogito.monitoring.elastic.common;

import java.time.Duration;

import org.junit.jupiter.api.Test;

import io.micrometer.elastic.ElasticConfig;

import static org.assertj.core.api.Assertions.assertThat;

public class ElasticConfigFactoryTest {

    @Test
    public void testGeneratedElasticConfig() {
        ElasticConfigFactory elasticConfigFactory = new ElasticConfigFactory();
        elasticConfigFactory.withProperty(KogitoElasticConfig.HOST_KEY, "http://mylocalhost");
        elasticConfigFactory.withProperty(KogitoElasticConfig.USERNAME_KEY, "pippo");
        elasticConfigFactory.withProperty(KogitoElasticConfig.PASSWORD_KEY, "pluto");
        elasticConfigFactory.withProperty(KogitoElasticConfig.STEP_KEY, "1s");

        ElasticConfig elasticConfig = elasticConfigFactory.getElasticConfig();

        assertThat(elasticConfig.host()).isEqualTo("http://mylocalhost");
        assertThat(elasticConfig.userName()).isEqualTo("pippo");
        assertThat(elasticConfig.password()).isEqualTo("pluto");
        assertThat(elasticConfig.step()).isEqualTo(Duration.ofSeconds(1));
    }
}

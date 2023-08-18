package org.kie.kogito.jobs.management.springboot;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;

import static org.assertj.core.api.Assertions.assertThat;

class AddonObjectMapperBuilderCustomizerTest {
    @Test
    void customizer() {
        Jackson2ObjectMapperBuilderCustomizer customizer = new AddonObjectMapperBuilderCustomizer().customizer();
        assertThat(customizer).isNotNull();
    }
}

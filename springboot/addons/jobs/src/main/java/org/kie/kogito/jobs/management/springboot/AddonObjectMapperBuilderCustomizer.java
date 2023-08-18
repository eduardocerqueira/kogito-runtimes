package org.kie.kogito.jobs.management.springboot;

import org.kie.kogito.jobs.service.api.serlialization.SerializationUtils;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AddonObjectMapperBuilderCustomizer {

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer customizer() {
        return builder -> builder.postConfigurer(SerializationUtils::registerDescriptors);
    }
}

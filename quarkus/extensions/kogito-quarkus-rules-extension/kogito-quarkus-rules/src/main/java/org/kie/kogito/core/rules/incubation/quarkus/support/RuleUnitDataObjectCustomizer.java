package org.kie.kogito.core.rules.incubation.quarkus.support;

import javax.enterprise.context.ApplicationScoped;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.quarkus.jackson.ObjectMapperCustomizer;

/**
 * Wraps and configures a custom ObjectMapper for DataSources
 */
@ApplicationScoped
class RuleUnitDataObjectCustomizer implements ObjectMapperCustomizer {

    @Override
    public void customize(ObjectMapper objectMapper) {
        objectMapper.registerModule(new RuleUnitDataJacksonModule());
    }
}

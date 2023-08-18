package org.kie.kogito.jobs.management.quarkus;

import javax.enterprise.context.ApplicationScoped;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.quarkus.jackson.ObjectMapperCustomizer;

import static org.kie.kogito.jobs.service.api.serlialization.SerializationUtils.registerDescriptors;

@ApplicationScoped
public class AddonObjectMapperCustomizer implements ObjectMapperCustomizer {
    @Override
    public void customize(ObjectMapper objectMapper) {
        registerDescriptors(objectMapper);
    }
}

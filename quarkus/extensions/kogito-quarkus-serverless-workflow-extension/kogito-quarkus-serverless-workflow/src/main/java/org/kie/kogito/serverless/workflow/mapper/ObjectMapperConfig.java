package org.kie.kogito.serverless.workflow.mapper;

import javax.enterprise.context.ApplicationScoped;

import org.kie.kogito.jackson.utils.JsonNodeFactoryListener;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.quarkus.jackson.ObjectMapperCustomizer;

@ApplicationScoped
public class ObjectMapperConfig implements ObjectMapperCustomizer {

    @Override
    public void customize(ObjectMapper objectMapper) {
        objectMapper.setNodeFactory(new JsonNodeFactoryListener());
    }
}

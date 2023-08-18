package org.kie.kogito.event.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonCloudEventDataFactory<T> extends AbstractCloudEventDataFactory<T> {

    private final ObjectMapper mapper;

    public JacksonCloudEventDataFactory(ObjectMapper objectMapper) {
        this.mapper = objectMapper;
    }

    @Override
    protected byte[] toBytes(T event) throws JsonProcessingException {
        return mapper.writeValueAsBytes(event);
    }
}

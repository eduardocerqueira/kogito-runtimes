package org.kie.kogito.event.impl;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.cloudevents.CloudEvent;

public class Object2JsonCloudEventConverter extends AbstractCloudEventConverter<Object> {

    private ObjectMapper objectMapper;

    public Object2JsonCloudEventConverter(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    protected CloudEvent toValue(Object value) throws IOException {
        return value instanceof CloudEvent ? (CloudEvent) value : objectMapper.readValue(value.toString(), CloudEvent.class);
    }

}

package org.kie.kogito.event.impl;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.cloudevents.CloudEvent;

public class StringCloudEventConverter extends AbstractCloudEventConverter<String> {

    private ObjectMapper objectMapper;

    public StringCloudEventConverter(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    protected CloudEvent toValue(String value) throws IOException {
        return objectMapper.readValue(value, CloudEvent.class);
    }

}

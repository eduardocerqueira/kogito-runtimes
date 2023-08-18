package org.kie.kogito.event.impl;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.cloudevents.CloudEvent;

public class ByteArray2JsonCloudEventConverter extends AbstractCloudEventConverter<byte[]> {

    private ObjectMapper objectMapper;

    public ByteArray2JsonCloudEventConverter(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    protected CloudEvent toValue(byte[] value) throws IOException {
        return objectMapper.readValue(value, CloudEvent.class);
    }

}

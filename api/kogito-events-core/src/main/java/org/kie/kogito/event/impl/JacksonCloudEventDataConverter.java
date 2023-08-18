package org.kie.kogito.event.impl;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonCloudEventDataConverter<O> extends AbstractCloudEventDataConverter<O> {

    public JacksonCloudEventDataConverter(ObjectMapper objectMapper, Class<O> outputClass) {
        super(objectMapper, outputClass);
    }

    @Override
    protected O toValue(byte[] value) throws IOException {
        return objectMapper.readValue(value, targetClass);
    }
}

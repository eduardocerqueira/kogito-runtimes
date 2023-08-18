package org.kie.kogito.workflows.services;

import java.io.IOException;

import org.kie.kogito.event.impl.AbstractCloudEventDataConverter;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JavaSerializationCloudEventDataConverter<O> extends AbstractCloudEventDataConverter<O> {

    protected JavaSerializationCloudEventDataConverter(ObjectMapper objectMapper, Class<O> targetClass) {
        super(objectMapper, targetClass);
    }

    @Override
    protected O toValue(byte[] bytes) throws IOException {
        return JavaSerializationUtils.fromBytes(bytes, targetClass, objectMapper);
    }
}

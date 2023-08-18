package org.kie.kogito.event.impl;

import org.kie.kogito.event.CloudEventUnmarshaller;
import org.kie.kogito.event.CloudEventUnmarshallerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ByteArrayCloudEventUnmarshallerFactory implements CloudEventUnmarshallerFactory<byte[]> {

    private final ObjectMapper objectMapper;

    public ByteArrayCloudEventUnmarshallerFactory(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public <S> CloudEventUnmarshaller<byte[], S> unmarshaller(Class<S> targetClass) {
        return new DefaultCloudEventUnmarshaller<>(new ByteArray2JsonCloudEventConverter(objectMapper),
                new JacksonCloudEventDataConverter<>(objectMapper, targetClass),
                new ByteArrayCloudEventDataConverter());
    }
}

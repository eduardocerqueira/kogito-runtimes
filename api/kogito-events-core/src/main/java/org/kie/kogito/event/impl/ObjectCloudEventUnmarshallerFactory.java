package org.kie.kogito.event.impl;

import org.kie.kogito.event.CloudEventUnmarshaller;
import org.kie.kogito.event.CloudEventUnmarshallerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectCloudEventUnmarshallerFactory implements CloudEventUnmarshallerFactory<Object> {
    private final ObjectMapper objectMapper;

    public ObjectCloudEventUnmarshallerFactory(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public <S> CloudEventUnmarshaller<Object, S> unmarshaller(Class<S> targetClass) {
        return new DefaultCloudEventUnmarshaller<>(new Object2JsonCloudEventConverter(objectMapper),
                new JacksonCloudEventDataConverter<>(objectMapper, targetClass),
                new ObjectCloudEventDataConverter());
    }
}

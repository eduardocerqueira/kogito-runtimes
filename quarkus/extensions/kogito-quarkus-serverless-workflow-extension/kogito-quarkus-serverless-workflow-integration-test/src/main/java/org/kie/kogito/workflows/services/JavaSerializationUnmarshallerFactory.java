package org.kie.kogito.workflows.services;

import org.kie.kogito.event.CloudEventUnmarshaller;
import org.kie.kogito.event.CloudEventUnmarshallerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JavaSerializationUnmarshallerFactory implements CloudEventUnmarshallerFactory<Object> {

    private final ObjectMapper objectMapper;

    public JavaSerializationUnmarshallerFactory(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public <S> CloudEventUnmarshaller<Object, S> unmarshaller(Class<S> targetClass) {
        return new JavaSerializationUnmarshaller<>(objectMapper, targetClass);
    }
}

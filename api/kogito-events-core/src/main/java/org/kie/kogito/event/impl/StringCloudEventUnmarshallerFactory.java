package org.kie.kogito.event.impl;

import org.kie.kogito.event.CloudEventUnmarshaller;
import org.kie.kogito.event.CloudEventUnmarshallerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

public class StringCloudEventUnmarshallerFactory implements CloudEventUnmarshallerFactory<String> {

    private final ObjectMapper objectMapper;

    public StringCloudEventUnmarshallerFactory(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public <S> CloudEventUnmarshaller<String, S> unmarshaller(Class<S> targetClass) {
        return new DefaultCloudEventUnmarshaller<>(new StringCloudEventConverter(objectMapper),
                new JacksonCloudEventDataConverter<>(objectMapper, targetClass),
                new String2JsonCloudEventDataConverter());
    }
}

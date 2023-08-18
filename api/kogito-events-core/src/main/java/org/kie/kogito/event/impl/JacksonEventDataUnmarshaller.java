package org.kie.kogito.event.impl;

import java.io.IOException;
import java.util.Objects;

import org.kie.kogito.event.EventUnmarshaller;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonEventDataUnmarshaller<S> implements EventUnmarshaller<S> {

    private ObjectMapper objectMapper;

    public JacksonEventDataUnmarshaller(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public <T> T unmarshall(S input, Class<T> outputClass, Class<?>... parametrizedClasses) throws IOException {
        if (input == null) {
            return null;
        } else if (outputClass.isAssignableFrom(input.getClass())) {
            return outputClass.cast(input);
        } else {
            final JavaType type = Objects.isNull(parametrizedClasses) ? objectMapper.getTypeFactory().constructType(outputClass)
                    : objectMapper.getTypeFactory().constructParametricType(outputClass, parametrizedClasses);
            return input instanceof byte[] ? objectMapper.readValue((byte[]) input, type) : objectMapper.readValue(input.toString(), type);
        }
    }
}

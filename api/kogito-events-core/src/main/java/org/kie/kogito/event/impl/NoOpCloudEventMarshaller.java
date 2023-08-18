package org.kie.kogito.event.impl;

import java.util.function.Function;

import org.kie.kogito.event.CloudEventMarshaller;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.cloudevents.CloudEvent;
import io.cloudevents.CloudEventData;

public class NoOpCloudEventMarshaller implements CloudEventMarshaller<Object> {

    private final ObjectMapper objectMapper;

    public NoOpCloudEventMarshaller(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public Object marshall(CloudEvent event) {
        return event;
    }

    @Override
    public <T> Function<T, CloudEventData> cloudEventDataFactory() {
        return new JacksonCloudEventDataFactory<>(objectMapper);
    }
}

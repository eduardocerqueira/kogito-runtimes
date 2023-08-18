package org.kie.kogito.event.impl;

import java.io.IOException;
import java.util.function.Function;

import org.kie.kogito.event.CloudEventMarshaller;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.cloudevents.CloudEvent;
import io.cloudevents.CloudEventData;

public class ByteArrayCloudEventMarshaller implements CloudEventMarshaller<byte[]> {

    private final ObjectMapper mapper;

    public ByteArrayCloudEventMarshaller(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public byte[] marshall(CloudEvent event) throws IOException {
        return mapper.writeValueAsBytes(event);
    }

    @Override
    public <T> Function<T, CloudEventData> cloudEventDataFactory() {
        return new JacksonCloudEventDataFactory<>(mapper);
    }
}

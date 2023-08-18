package org.kie.kogito.event.impl;

import org.kie.kogito.event.EventMarshaller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ByteArrayEventMarshaller implements EventMarshaller<byte[]> {
    private final ObjectMapper mapper;

    public ByteArrayEventMarshaller(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public <T> byte[] marshall(T event) throws JsonProcessingException {
        return mapper.writeValueAsBytes(event);
    }
}

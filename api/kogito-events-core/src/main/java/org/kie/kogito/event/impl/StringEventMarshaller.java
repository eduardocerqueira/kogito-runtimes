package org.kie.kogito.event.impl;

import org.kie.kogito.event.EventMarshaller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class StringEventMarshaller implements EventMarshaller<String> {

    private final ObjectMapper mapper;

    public StringEventMarshaller(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public <T> String marshall(T event) throws JsonProcessingException {
        return mapper.writeValueAsString(event);
    }
}

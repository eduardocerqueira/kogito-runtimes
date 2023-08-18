package org.kie.kogito.jackson.utils;

import java.util.function.Function;
import java.util.function.Supplier;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonNodeConverter implements Function<String, JsonNode> {

    private Supplier<ObjectMapper> objectMapper;

    public JsonNodeConverter() {
        this(ObjectMapperFactory::get);
    }

    public JsonNodeConverter(Supplier<ObjectMapper> mapper) {
        this.objectMapper = mapper;
    }

    @Override
    public JsonNode apply(String t) {
        try {
            return objectMapper.get().readTree(t);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Invalid value for json node " + t);
        }
    }

}

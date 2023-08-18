package org.kie.kogito.jackson.utils;

import java.util.function.Function;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;

public class StringConverter implements Function<JsonNode, String> {

    @Override
    public String apply(JsonNode t) {
        try {
            return JsonObjectUtils.toString(t);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Invalid value for json node " + t);
        }
    }
}

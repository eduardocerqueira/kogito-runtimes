package org.jbpm.process.core.datatype.impl.coverter;

import java.util.function.Function;

import org.kie.kogito.jackson.utils.ObjectMapperFactory;

import com.fasterxml.jackson.core.JsonProcessingException;

public class JacksonUnconverter<T> implements Function<T, String> {

    @Override
    public String apply(T t) {
        try {
            return ObjectMapperFactory.get().writeValueAsString(t);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
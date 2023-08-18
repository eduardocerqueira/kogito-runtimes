package org.jbpm.process.core.datatype.impl.coverter;

import java.util.function.Function;

import org.kie.kogito.jackson.utils.ObjectMapperFactory;

import com.fasterxml.jackson.core.JsonProcessingException;

public class JacksonConverter<T> implements Function<String, T> {

    private final Class<T> clazz;

    public JacksonConverter(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public T apply(String t) {
        try {
            return ObjectMapperFactory.get().readValue(t, clazz);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException(e);
        }
    }
}

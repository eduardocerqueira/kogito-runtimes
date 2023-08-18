package org.kie.kogito.jackson.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

import io.cloudevents.jackson.JsonFormat;

public class ObjectMapperFactory {

    private ObjectMapperFactory() {
    }

    private static class DefaultObjectMapper {
        private static ObjectMapper instance = JsonMapper.builder()
                .enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS)
                .build()
                .setSerializationInclusion(JsonInclude.Include.NON_NULL)
                .setTypeFactory(TypeFactory.defaultInstance().withClassLoader(Thread.currentThread().getContextClassLoader()))
                .registerModule(JsonFormat.getCloudEventJacksonModule())
                .findAndRegisterModules();
    }

    private static class ListenerAwareMapper {
        private static ObjectMapper instance = DefaultObjectMapper.instance.copy().setNodeFactory(new JsonNodeFactoryListener());
    }

    public static ObjectMapper get() {
        return DefaultObjectMapper.instance;
    }

    public static ObjectMapper listenerAware() {
        return ListenerAwareMapper.instance;
    }
}

package org.kie.kogito.event.impl;

import java.io.IOException;
import java.util.Optional;

import org.kie.kogito.event.Converter;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.cloudevents.CloudEventData;
import io.cloudevents.core.data.PojoCloudEventData;
import io.cloudevents.jackson.JsonCloudEventData;
import io.cloudevents.jackson.PojoCloudEventDataMapper;

public abstract class AbstractCloudEventDataConverter<O> implements Converter<CloudEventData, O> {

    protected final ObjectMapper objectMapper;
    protected final Class<O> targetClass;

    protected AbstractCloudEventDataConverter(ObjectMapper objectMapper, Class<O> targetClass) {
        this.objectMapper = objectMapper;
        this.targetClass = targetClass;
    }

    @Override
    public O convert(CloudEventData value) throws IOException {
        if (value == null) {
            return null;
        }
        Optional<O> target = isTargetInstanceAlready(value);
        return target.isPresent() ? target.get() : toValue(value.toBytes());
    }

    protected Optional<O> isTargetInstanceAlready(CloudEventData value) {
        if (value instanceof PojoCloudEventData) {
            Object pojo = ((PojoCloudEventData<?>) value).getValue();
            return Optional.of(targetClass.isAssignableFrom(pojo.getClass()) ? targetClass.cast(pojo) : PojoCloudEventDataMapper.from(objectMapper, targetClass).map(value).getValue());
        } else if (value instanceof JsonCloudEventData) {
            JsonNode node = ((JsonCloudEventData) value).getNode();
            return Optional.of(JsonNode.class.isAssignableFrom(targetClass) ? targetClass.cast(node) : objectMapper.convertValue(node, targetClass));
        }
        return Optional.empty();
    }

    protected abstract O toValue(byte[] value) throws IOException;
}

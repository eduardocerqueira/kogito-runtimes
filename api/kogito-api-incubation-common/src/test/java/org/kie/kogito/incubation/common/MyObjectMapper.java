package org.kie.kogito.incubation.common;

import java.util.Map;

import org.kie.kogito.incubation.common.objectmapper.InternalObjectMapper;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MyObjectMapper implements InternalObjectMapper {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
    }

    public <T> T convertValue(Object self, Class<T> type) {
        if (type.isInstance(self)) {
            return type.cast(self);
        }
        if (MapLikeDataContext.class == type || MapDataContext.class == type) {
            return (T) MapDataContext.of(objectMapper.convertValue(self, Map.class));
        }
        if (ExtendedDataContext.class == type) {
            if (self instanceof DataContext) {
                return (T) ExtendedDataContext.ofData((DataContext) self);
            } else {
                return (T) ExtendedDataContext.ofData(convertValue(self, MapDataContext.class));
            }
        }
        return objectMapper.convertValue(self, type);
    }
}

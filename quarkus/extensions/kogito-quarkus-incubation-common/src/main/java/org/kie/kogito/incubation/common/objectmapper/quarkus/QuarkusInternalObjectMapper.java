package org.kie.kogito.incubation.common.objectmapper.quarkus;

import java.util.Map;

import javax.enterprise.inject.spi.CDI;

import org.kie.kogito.incubation.common.DataContext;
import org.kie.kogito.incubation.common.ExtendedDataContext;
import org.kie.kogito.incubation.common.MapDataContext;
import org.kie.kogito.incubation.common.MapLikeDataContext;
import org.kie.kogito.incubation.common.objectmapper.InternalObjectMapper;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * This class bridges CDI to non-CDI classes by using <code>CDI.current().select()</code>
 */
public class QuarkusInternalObjectMapper implements InternalObjectMapper {

    @Override
    public <T> T convertValue(Object self, Class<T> type) {
        if (type.isInstance(self)) {
            return type.cast(self);
        }

        ObjectMapper objectMapper =
                CDI.current().select(ObjectMapper.class).get();

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

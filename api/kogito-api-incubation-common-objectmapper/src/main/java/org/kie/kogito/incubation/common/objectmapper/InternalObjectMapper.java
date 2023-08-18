package org.kie.kogito.incubation.common.objectmapper;

import java.util.ServiceLoader;

/**
 * For internal use only.
 * Provides a method to convert an object into a given type.
 * This is an implementation detail. We may move this to a separate module in the future.
 */
public interface InternalObjectMapper {
    <T> T convertValue(Object self, Class<T> type);

    public static InternalObjectMapper objectMapper() {
        return ServiceLoader.load(InternalObjectMapper.class).findFirst()
                .orElseThrow(MissingInternalObjectMapper::new);
    }
}

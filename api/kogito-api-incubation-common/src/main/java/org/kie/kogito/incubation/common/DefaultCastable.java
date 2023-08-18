package org.kie.kogito.incubation.common;

import org.kie.kogito.incubation.common.objectmapper.InternalObjectMapper;

/**
 * Utility interface, useful to mix-in to get a default `as` implementation.
 * <p>
 * Provides a default implementation for the {@link #as(Class)} method,
 * delegating to {@link org.kie.kogito.incubation.common.objectmapper.InternalObjectMapper#convertValue(Object, Class)}
 */
public interface DefaultCastable extends Castable {

    default <T extends DataContext> T as(Class<T> type) {
        if (type.isInstance(this)) {
            return type.cast(this);
        }
        return InternalObjectMapper.objectMapper().convertValue(this, type);
    }
}

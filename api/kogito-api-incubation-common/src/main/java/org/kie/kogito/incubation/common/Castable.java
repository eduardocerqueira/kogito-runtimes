package org.kie.kogito.incubation.common;

/**
 * Represents a type that can be converted into a {@link DataContext}
 */
public interface Castable {
    /**
     * Converts this object into the given type.
     */
    <T extends DataContext> T as(Class<T> type);
}

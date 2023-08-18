package org.kie.kogito.codegen.core.events;

import java.util.Set;

import org.kie.kogito.event.cloudevents.CloudEventMeta;

/**
 * {@link CloudEventMeta} builder
 *
 * @param <C> the {@link CloudEventMeta} DTO for the given context
 * @param <S> the source to extract the {@link CloudEventMeta} information
 */
public interface CloudEventMetaBuilder<C extends CloudEventMeta, S> {

    /**
     * Generates a {@link Set} of {@link CloudEventMeta} objects based on the given engine model
     *
     * @param sourceModel the given executor model
     */
    Set<C> build(S sourceModel);
}

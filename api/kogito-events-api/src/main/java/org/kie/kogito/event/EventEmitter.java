package org.kie.kogito.event;

import java.util.concurrent.CompletionStage;

/**
 * It is responsible to interact with the external event service for event publishing.
 * One of its task is to transform the data event into the format expected by the external service.
 * 
 * @see EventMarshaller
 * @see CloudEventMarshaller
 */
public interface EventEmitter extends AutoCloseable {
    /**
     * Publish the data event object into an external event service.
     * 
     * @param dataEvent The DataEvent
     */
    CompletionStage<Void> emit(DataEvent<?> dataEvent);

    @Override
    default void close() throws Exception {
    }
}

package org.kie.kogito.event;

import java.util.concurrent.CompletionStage;
import java.util.function.Function;

/**
 * Event receiver interface.
 * 
 * Implementation are responsible for interacting with the external event publisher and transforming the events received into the model object.
 * 
 * @see EventUnmarshaller
 * @see CloudEventUnmarshaller
 */
public interface EventReceiver extends AutoCloseable {

    /**
     * Subscribe an event consumer for a receiver. The implementation will receive the event (in some format) from the external service, transform it
     * into a data event instance and invoke the callback.
     * 
     * @param consumer consumer function that accepts the data event object and return a completion stage with the result of the consumption.
     * @param dataClass the model object class wrapped into the data event
     */
    <T> void subscribe(Function<DataEvent<T>, CompletionStage<?>> consumer, Class<T> dataClass);

    @Override
    default void close() throws Exception {
    }
}

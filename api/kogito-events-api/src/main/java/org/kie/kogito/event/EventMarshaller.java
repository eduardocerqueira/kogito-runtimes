package org.kie.kogito.event;

import java.io.IOException;

/**
 * This interface is one of the extension point for customers to incorporate more event formats.
 * It is responsible for converting Kogito business objects into the format expected by the external service when not using cloud events.
 * 
 * @param <R> The expected output type that will be consumed by the external service
 */
public interface EventMarshaller<R> {
    /**
     * Converts Kogito business object into external service one
     * 
     * @param <T> Kogito business object type
     * @param event Kogito business object.
     * @return
     */
    <T> R marshall(T event) throws IOException;
}

package org.kie.kogito.event;

import java.io.IOException;

/**
 * This interface is one of the extension point for customers to incorporate more event formats when NOT using cloud events.
 * It is responsible for converting objects received in the external service format into business objects consumed by Kogito.
 * Default implementation of Kogito uses Jackson.
 *
 * @param <S> the external service object type
 */
public interface EventUnmarshaller<S> {

    /**
     * Convert object received from the external service, denoted by generic type S, into a business object
     * 
     * @param <T> business object type
     * @param input object received by the external system
     * @param outputClass target business class
     * @param parametrizedClasses business class might use generics
     * @return kogito business object
     * @throws IOException if there is any problem with the format. This method should not willingly throw any runtime
     */
    <T> T unmarshall(S input, Class<T> outputClass, Class<?>... parametrizedClasses) throws IOException;
}

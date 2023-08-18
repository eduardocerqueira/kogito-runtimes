package org.kie.kogito.event;

/**
 * This interface is one of the extension point for customers to incorporate more event formats when using cloud events.
 * It is responsible for creating CloudEventUnmarshaller instance for an specific business target class.
 *
 * @param <T> External service object type
 */
public interface CloudEventUnmarshallerFactory<T> {
    /**
     * Creates a Cloud event unmarshaller object
     * 
     * @param <S> external service input type
     * @param targetClass target business object type
     * @return Unmarshaller object that will be used to create CloudEvents for that type
     */
    <S> CloudEventUnmarshaller<T, S> unmarshaller(Class<S> targetClass);
}

package org.kie.kogito.addon.quarkus.messaging.common.message;

import javax.ws.rs.core.HttpHeaders;

import org.eclipse.microprofile.reactive.messaging.Message;
import org.kie.kogito.addon.quarkus.common.reactive.messaging.MessageDecorator;

import io.quarkus.reactivemessaging.http.runtime.OutgoingHttpMetadata;

/**
 * Decorators for Http CloudEvents outgoing messages
 */
public class CloudEventHttpOutgoingDecorator implements MessageDecorator {

    // Note: this constant is also declared in cloudevents-json-jackson.
    // However, to avoid importing a library for only one constant that won't likely to change, we opt to have it declared here.
    public static final String CLOUD_EVENTS_CONTENT_TYPE = "application/cloudevents+json";

    /**
     * Metadata to include content-type for structured CloudEvents messages
     */
    static final OutgoingHttpMetadata HTTP_RESPONSE_METADATA =
            new OutgoingHttpMetadata.Builder().addHeader(HttpHeaders.CONTENT_TYPE, CLOUD_EVENTS_CONTENT_TYPE).build();

    /**
     * Decorates a given payload with custom metadata needed by Http Outgoing processing
     *
     * @param message of the given message
     * @param <T> Payload type
     */
    @Override
    public <T> Message<T> decorate(Message<T> message) {
        return message.addMetadata(HTTP_RESPONSE_METADATA);
    }
}

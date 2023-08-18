package org.kie.kogito.addon.quarkus.common.reactive.messaging;

import org.eclipse.microprofile.reactive.messaging.Message;

/**
 * {@link MessageDecorator}s can decorates the {@link Message} envelope with metadata and additional information in a given context.
 */
public interface MessageDecorator extends Comparable<MessageDecorator> {

    /**
     * Decorates the given reactive message
     *
     * @param payload payload to decorate
     * @param <T> payload type
     * @return payload in Message format decorated
     */
    <T> Message<T> decorate(Message<T> message);

    default int priority() {
        return 100;
    }

    @Override
    default int compareTo(MessageDecorator o) {
        return priority() - o.priority();
    }
}

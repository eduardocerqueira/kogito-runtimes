package org.kie.kogito.addon.quarkus.common.reactive.messaging;

import java.util.Collection;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

import org.eclipse.microprofile.reactive.messaging.Message;

/**
 * Provides a {@link MessageDecorator} instance. Ideally should not be used outside the {@link MessageFactory}.
 */
@ApplicationScoped
public class MessageDecoratorProvider {

    @Inject
    Instance<MessageDecorator> messageDecorators;

    private Collection<MessageDecorator> sortedMessageDecorators;

    @PostConstruct
    void init() {
        sortedMessageDecorators = messageDecorators.stream().sorted().collect(Collectors.toList());
    }

    /**
     * Builds a new {@link MessageDecorator} depending on the implementation being presented in the classpath.
     *
     * @return an instance of {@link MessageDecorator}
     */
    public <T> Message<T> decorate(Message<T> message) {
        for (MessageDecorator messageDecorator : sortedMessageDecorators) {
            message = messageDecorator.decorate(message);
        }
        return message;
    }
}

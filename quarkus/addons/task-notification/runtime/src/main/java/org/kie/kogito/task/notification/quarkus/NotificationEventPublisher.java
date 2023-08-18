package org.kie.kogito.task.notification.quarkus;

import java.util.Collection;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.kie.kogito.event.DataEvent;
import org.kie.kogito.event.EventPublisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class NotificationEventPublisher implements EventPublisher {

    private static final Logger logger = LoggerFactory.getLogger(NotificationEventPublisher.class.getName());
    private static final String CHANNEL_NAME = "kogito-deadline-events";

    @Inject
    @Channel(CHANNEL_NAME)
    Emitter<DataEvent<?>> emitter;

    @Override
    public void publish(DataEvent<?> event) {
        if (event.getType().startsWith("UserTaskDeadline")) {
            logger.debug("About to publish event {} to topic {}", event, CHANNEL_NAME);
            try {
                emitter.send(event);
                logger.debug("Successfully published event {} to topic {}", event, CHANNEL_NAME);
            } catch (Exception e) {
                logger.error("Error while publishing event to topic {} for event {}", CHANNEL_NAME, event, e);
            }
        } else {
            logger.debug("Unknown type of event '{}', ignoring", event.getType());
        }
    }

    @Override
    public void publish(Collection<DataEvent<?>> events) {
        for (DataEvent<?> event : events) {
            publish(event);
        }
    }
}

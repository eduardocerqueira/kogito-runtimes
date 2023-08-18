package org.kie.kogito.task.notification.spring;

import java.util.Collection;

import org.kie.kogito.event.DataEvent;
import org.kie.kogito.event.EventPublisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class SpringNotificationEventPublisher implements EventPublisher {

    private static final Logger logger = LoggerFactory.getLogger(SpringNotificationEventPublisher.class);

    @Autowired
    private KafkaTemplate<String, DataEvent<?>> emitter;

    @Value("${kogito.events.deadline.topic:kogito-deadline-events}")
    private String topic;

    @Override
    public void publish(DataEvent<?> event) {
        if (event.getType().startsWith("UserTaskDeadline")) {
            logger.debug("About to publish event {} to Kafka topic {}", event, topic);
            try {
                emitter.send(topic, event);
                logger.debug("Successfully published event {} to topic {}", event, topic);
            } catch (Exception e) {
                logger.error("Error while publishing event to Kafka topic {} for event {}", topic, event, e);
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

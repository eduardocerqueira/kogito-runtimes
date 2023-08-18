package org.kie.kogito.mail;

import org.kie.kogito.event.process.UserTaskDeadlineDataEvent;

import io.quarkus.kafka.client.serialization.ObjectMapperDeserializer;

public class DeadlineEventDeserializer extends ObjectMapperDeserializer<UserTaskDeadlineDataEvent> {

    public DeadlineEventDeserializer() {
        super(UserTaskDeadlineDataEvent.class);
    }
}

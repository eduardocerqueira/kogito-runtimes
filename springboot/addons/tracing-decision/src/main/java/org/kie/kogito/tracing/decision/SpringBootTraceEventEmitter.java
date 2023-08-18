package org.kie.kogito.tracing.decision;

import org.kie.kogito.tracing.EventEmitter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class SpringBootTraceEventEmitter implements EventEmitter {

    private final KafkaTemplate<String, String> template;
    private final String kafkaTopicName;

    @Autowired
    public SpringBootTraceEventEmitter(final KafkaTemplate<String, String> template,
            final @Value(value = "${kogito.addon.tracing.decision.kafka.topic.name:kogito-tracing-decision}") String kafkaTopicName) {
        this.template = template;
        this.kafkaTopicName = kafkaTopicName;
    }

    @Override
    public void emit(final String payload) {
        template.send(kafkaTopicName, payload);
    }
}

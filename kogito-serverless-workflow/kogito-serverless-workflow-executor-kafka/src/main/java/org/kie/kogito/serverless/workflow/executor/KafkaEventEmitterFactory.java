package org.kie.kogito.serverless.workflow.executor;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.kie.kogito.event.EventEmitter;
import org.kie.kogito.event.EventEmitterFactory;

import io.cloudevents.CloudEvent;

public class KafkaEventEmitterFactory implements EventEmitterFactory {

    private Map<String, String> trigger2Topic = KafkaPropertiesFactory.get().triggerToTopicMap("kogito.addon.messaging.incoming.trigger.");
    private Map<String, EventEmitter> emitters = new ConcurrentHashMap<>();
    private Producer<byte[], CloudEvent> producer;

    @Override
    public EventEmitter apply(String trigger) {
        return emitters.computeIfAbsent(trigger2Topic.getOrDefault(trigger, trigger), this::createEmitter);
    }

    private EventEmitter createEmitter(String trigger) {
        synchronized (this) {
            if (producer == null) {
                producer = createKafkaProducer();
            }
        }
        return new KafkaEventEmitter(producer, trigger);
    }

    @Override
    public void close() {
        if (producer != null) {
            producer.close();
        }
    }

    protected Producer<byte[], CloudEvent> createKafkaProducer() {
        return new KafkaProducer<>(KafkaPropertiesFactory.get().getKafkaProducerConfig());
    }
}

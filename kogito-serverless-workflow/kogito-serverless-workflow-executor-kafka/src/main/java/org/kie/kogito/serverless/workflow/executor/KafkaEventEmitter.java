package org.kie.kogito.serverless.workflow.executor;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.kie.kogito.event.DataEvent;
import org.kie.kogito.event.EventEmitter;

import com.fasterxml.jackson.databind.JsonNode;

import io.cloudevents.CloudEvent;
import io.cloudevents.jackson.JsonCloudEventData;

public class KafkaEventEmitter implements EventEmitter {

    private final Producer<byte[], CloudEvent> kafkaProducer;
    private final String topic;

    public KafkaEventEmitter(Producer<byte[], CloudEvent> kafkaProducer, String topic) {
        this.kafkaProducer = kafkaProducer;
        this.topic = topic;
    }

    @Override
    public CompletionStage<Void> emit(DataEvent<?> dataEvent) {
        kafkaProducer.send(new ProducerRecord<>(topic, dataEvent.asCloudEvent(o -> JsonCloudEventData.wrap((JsonNode) o))));
        return CompletableFuture.completedStage(null);
    }
}

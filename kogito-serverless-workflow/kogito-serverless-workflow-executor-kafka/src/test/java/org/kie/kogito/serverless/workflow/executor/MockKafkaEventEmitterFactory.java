package org.kie.kogito.serverless.workflow.executor;

import org.apache.kafka.clients.producer.MockProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.common.header.internals.RecordHeaders;
import org.apache.kafka.common.serialization.ByteArraySerializer;

import io.cloudevents.CloudEvent;
import io.cloudevents.kafka.CloudEventSerializer;

public class MockKafkaEventEmitterFactory extends KafkaEventEmitterFactory {

    public static MockProducer<byte[], CloudEvent> producer = new MockProducer<>(true, new ByteArraySerializer(), new CloudEventSerializer() {
        @Override
        public byte[] serialize(String topic, CloudEvent data) {
            return super.serialize(topic, new RecordHeaders(), data);
        }
    });

    @Override
    public int ordinal() {
        return 1;
    }

    @Override
    protected Producer<byte[], CloudEvent> createKafkaProducer() {
        return producer;
    }
}

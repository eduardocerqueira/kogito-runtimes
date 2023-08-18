package org.kie.kogito.test.quarkus.kafka;

import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;

public class KafkaTestClient extends KafkaTypedTestClient<String, StringSerializer, StringDeserializer> {
    public KafkaTestClient(String hosts) {
        super(hosts, StringSerializer.class, StringDeserializer.class);
    }
}

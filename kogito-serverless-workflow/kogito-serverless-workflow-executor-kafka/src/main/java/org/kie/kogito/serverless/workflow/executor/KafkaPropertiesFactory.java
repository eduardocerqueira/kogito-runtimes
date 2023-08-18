package org.kie.kogito.serverless.workflow.executor;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.ByteArrayDeserializer;
import org.apache.kafka.common.serialization.ByteArraySerializer;
import org.kie.kogito.serverless.workflow.utils.ConfigResolverHolder;

import io.cloudevents.kafka.CloudEventDeserializer;
import io.cloudevents.kafka.CloudEventSerializer;

public class KafkaPropertiesFactory {

    private static final KafkaPropertiesFactory INSTANCE = new KafkaPropertiesFactory();

    public static KafkaPropertiesFactory get() {
        return INSTANCE;
    }

    private Map<String, Object> configMap = ConfigResolverHolder.getConfigResolver().asMap();

    private void initCommonProperties(Map<String, Object> map) {
        map.putIfAbsent(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
    }

    public Map<String, Object> getKafkaProducerConfig() {
        Map<String, Object> map = new HashMap<>(configMap);
        initCommonProperties(map);
        map.putIfAbsent(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, CloudEventSerializer.class);
        map.putIfAbsent(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, ByteArraySerializer.class);
        return map;
    }

    public Map<String, Object> getKafkaConsumerConfig() {
        Map<String, Object> map = new HashMap<>(configMap);
        initCommonProperties(map);
        map.putIfAbsent(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, CloudEventDeserializer.class);
        map.putIfAbsent(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, ByteArrayDeserializer.class);
        map.putIfAbsent(ConsumerConfig.GROUP_ID_CONFIG, "serverless-workflow-executor");
        map.putIfAbsent(ConsumerConfig.CLIENT_ID_CONFIG, UUID.randomUUID().toString());
        return map;
    }

    public Map<String, String> triggerToTopicMap(String prefix) {
        Map<String, String> trigger2Topic = new HashMap<>();
        for (Map.Entry<String, Object> entry : configMap.entrySet()) {
            if (entry.getKey().startsWith(prefix)) {
                trigger2Topic.put(entry.getKey().substring(prefix.length()), entry.getValue().toString());
            }
        }
        return trigger2Topic;
    }

    private KafkaPropertiesFactory() {
    }
}

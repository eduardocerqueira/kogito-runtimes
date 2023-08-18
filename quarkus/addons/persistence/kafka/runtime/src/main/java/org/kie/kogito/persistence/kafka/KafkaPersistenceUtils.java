package org.kie.kogito.persistence.kafka;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.utils.Bytes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.state.KeyValueStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KafkaPersistenceUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaPersistenceUtils.class);
    private static final String TOPIC = "kogito.process";
    private static final String STORE = "kogito-store";

    private KafkaPersistenceUtils() {
    }

    public static String topicName() {
        return TOPIC;
    }

    public static String storeName() {
        return STORE;
    }

    public static Topology createTopologyForProcesses() {
        StreamsBuilder builder = new StreamsBuilder();
        builder.globalTable(topicName(), Materialized.<String, byte[], KeyValueStore<Bytes, byte[]>> as(storeName())
                .withKeySerde(Serdes.String())
                .withValueSerde(Serdes.ByteArray()));
        LOGGER.info("Created Kafka Stream GlobalTable");
        return builder.build();
    }
}

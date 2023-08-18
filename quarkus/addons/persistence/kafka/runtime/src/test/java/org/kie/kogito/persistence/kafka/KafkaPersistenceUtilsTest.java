package org.kie.kogito.persistence.kafka;

import org.apache.kafka.streams.Topology;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.kie.kogito.persistence.kafka.KafkaPersistenceUtils.createTopologyForProcesses;
import static org.kie.kogito.persistence.kafka.KafkaPersistenceUtils.storeName;
import static org.kie.kogito.persistence.kafka.KafkaPersistenceUtils.topicName;

public class KafkaPersistenceUtilsTest {

    @Test
    public void testTopicName() {
        assertThat(topicName()).isEqualTo("kogito.process");
    }

    @Test
    public void testStoreName() {
        assertThat(storeName()).isEqualTo("kogito-store");
    }

    @Test
    public void testTopology() {
        Topology topology = createTopologyForProcesses();
        assertThat(topology.describe().globalStores()).hasSize(1);
    }
}

package org.kie.kogito.persistence.kafka;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

import org.apache.kafka.streams.Topology;

import static org.kie.kogito.persistence.kafka.KafkaPersistenceUtils.createTopologyForProcesses;

@ApplicationScoped
public class KafkaStreamsTopologyProducer {

    @Produces
    public Topology buildTopology() {
        return createTopologyForProcesses();
    }
}

package org.kie.kogito.persistence;

import java.util.Arrays;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

import org.apache.kafka.streams.Topology;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.kie.kogito.persistence.kafka.KafkaPersistenceUtils.createTopologyForProcesses;

@ApplicationScoped
public class KafkaStreamsTopologyProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaStreamsTopologyProducer.class);

    private List<String> processes = Arrays.asList();

    @Produces
    public Topology buildTopology() {
        return createTopologyForProcesses(processes);
    }
}

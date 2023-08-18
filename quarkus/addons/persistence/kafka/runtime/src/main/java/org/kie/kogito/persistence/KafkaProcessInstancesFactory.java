package org.kie.kogito.persistence;

import java.util.Map;

import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.common.serialization.ByteArraySerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.kie.kogito.persistence.kafka.KafkaProcessInstances;
import org.kie.kogito.persistence.kafka.KafkaStreamsStateListener;
import org.kie.kogito.process.Process;
import org.kie.kogito.process.ProcessInstancesFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.smallrye.common.annotation.Identifier;

@ApplicationScoped
public class KafkaProcessInstancesFactory implements ProcessInstancesFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProcessInstancesFactory.class);

    KafkaStreamsStateListener stateListener;
    KafkaProducer<String, byte[]> producer;

    @Inject
    public void setStateListener(KafkaStreamsStateListener stateListener) {
        this.stateListener = stateListener;
    }

    @Inject
    public void setKafkaConfig(@Identifier("default-kafka-broker") Map<String, Object> kafkaConfig) {
        producer = new KafkaProducer<>(kafkaConfig, new StringSerializer(), new ByteArraySerializer());
    }

    @PreDestroy
    public void stop() {
        if (producer != null) {
            producer.close();
        }
    }

    public KafkaProcessInstances createProcessInstances(Process<?> process) {
        try {
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("Creating KafkaProcessInstances for process: {}", process.id());
            }
            KafkaProcessInstances pi = new KafkaProcessInstances(process, producer);
            stateListener.addProcessInstances(pi);
            return pi;
        } catch (Exception ex) {
            LOGGER.error("Error creating KafkaProcessInstances for process: {}", process.id(), ex);
            throw new RuntimeException("Error creating KafkaProcessInstances for process: " + process.id(), ex);
        }
    }

}

package org.kie.kogito.persistence.kafka.quarkus.deployment;

import org.kie.kogito.persistence.kafka.KafkaPersistenceUtils;
import org.kie.kogito.quarkus.addons.common.deployment.KogitoCapability;
import org.kie.kogito.quarkus.addons.common.deployment.OneOfCapabilityKogitoAddOnProcessor;

import io.quarkus.deployment.annotations.BuildStep;
import io.quarkus.deployment.builditem.FeatureBuildItem;
import io.quarkus.deployment.builditem.RunTimeConfigurationDefaultBuildItem;

class KogitoAddOnPersistenceKafkaProcessor extends OneOfCapabilityKogitoAddOnProcessor {

    private static final String FEATURE = "kogito-addon-persistence-kafka-extension";
    private static final String QUARKUS_KAFKA_STREAMS_TOPICS_PROP = "quarkus.kafka-streams.topics";

    KogitoAddOnPersistenceKafkaProcessor() {
        super(KogitoCapability.PROCESSES, KogitoCapability.SERVERLESS_WORKFLOW);
    }

    @BuildStep
    FeatureBuildItem feature() {
        return new FeatureBuildItem(FEATURE);
    }

    @BuildStep
    RunTimeConfigurationDefaultBuildItem runTimeConfiguration() {
        return new RunTimeConfigurationDefaultBuildItem(QUARKUS_KAFKA_STREAMS_TOPICS_PROP, KafkaPersistenceUtils.topicName());
    }
}

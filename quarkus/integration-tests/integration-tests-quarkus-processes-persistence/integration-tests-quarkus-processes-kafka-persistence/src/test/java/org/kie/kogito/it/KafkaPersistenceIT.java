package org.kie.kogito.it;

import org.kie.kogito.testcontainers.quarkus.KafkaQuarkusTestResource;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.common.ResourceArg;
import io.quarkus.test.junit.QuarkusIntegrationTest;

import static org.kie.kogito.testcontainers.quarkus.KafkaQuarkusTestResource.KOGITO_KAFKA_TOPICS;

@QuarkusIntegrationTest
@QuarkusTestResource(value = KafkaQuarkusTestResource.class, initArgs = { @ResourceArg(name = KOGITO_KAFKA_TOPICS, value = KafkaPersistenceIT.TOPICS) })
public class KafkaPersistenceIT extends PersistenceTest {

    public static final String TOPICS = "kogito.process";

}

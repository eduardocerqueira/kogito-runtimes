package org.kie.kogito.testcontainers.springboot;

import java.util.Map;

import org.kie.kogito.test.resources.ConditionalSpringBootTestResource;
import org.kie.kogito.testcontainers.KogitoKafkaContainer;

import static java.util.Collections.singletonMap;

/**
 * Kafka spring boot resource that works within the test lifecycle.
 *
 */
public class KafkaSpringBootTestResource extends ConditionalSpringBootTestResource<KogitoKafkaContainer> {

    public static final String KOGITO_KAFKA_PROPERTY = "spring.kafka.bootstrap-servers";

    public KafkaSpringBootTestResource() {
        super(new KogitoKafkaContainer());
    }

    @Override
    protected Map<String, String> getProperties() {
        return singletonMap(KOGITO_KAFKA_PROPERTY, "localhost:" + getTestResource().getMappedPort());
    }

    public static class Conditional extends KafkaSpringBootTestResource {

        public Conditional() {
            super();
            enableConditional();
        }
    }

}

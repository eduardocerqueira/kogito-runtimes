package org.kie.kogito.testcontainers.quarkus;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kie.kogito.testcontainers.KogitoKafkaContainer;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.spy;

public class KafkaQuarkusTestResourceTest {

    private KafkaQuarkusTestResource resource;

    private static final String IMAGE = "vectorized/redpanda:latest";

    @BeforeEach
    public void setup() {
        System.setProperty(KogitoKafkaContainer.KAFKA_PROPERTY, IMAGE);
    }

    @Test
    public void shouldGetProperty() {
        givenResource();
        assertThrows(IllegalStateException.class, () -> resource.getProperties().get(KafkaQuarkusTestResource.KOGITO_KAFKA_PROPERTY));
    }

    @Test
    public void shouldConditionalBeDisabledByDefault() {
        givenResource();
        thenConditionalIsDisabled();
    }

    @Test
    public void shouldConditionalBeEnabled() {
        givenConditionalResource();
        thenConditionalIsEnabled();
    }

    private void givenConditionalResource() {
        resource = spy(new KafkaQuarkusTestResource.Conditional());
    }

    private void givenResource() {
        resource = spy(new KafkaQuarkusTestResource());
    }

    private void thenConditionalIsEnabled() {
        assertTrue(resource.isConditionalEnabled());
    }

    private void thenConditionalIsDisabled() {
        assertFalse(resource.isConditionalEnabled());
    }
}

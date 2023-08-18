package org.kie.kogito.testcontainers.quarkus;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kie.kogito.testcontainers.KogitoInfinispanContainer;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.kie.kogito.testcontainers.Constants.CONTAINER_NAME_PREFIX;
import static org.mockito.Mockito.spy;

public class InfinispanQuarkusTestResourceTest {

    private static final String IMAGE = "my-infinispan-image";

    private InfinispanQuarkusTestResource resource;

    @BeforeEach
    public void setup() {
        System.setProperty(CONTAINER_NAME_PREFIX + KogitoInfinispanContainer.NAME, IMAGE);
    }

    @Test
    public void shouldGetProperty() {
        givenResource();
        assertThrows(IllegalStateException.class, () -> resource.getProperties().get(InfinispanQuarkusTestResource.KOGITO_INFINISPAN_PROPERTY));
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

    private void givenResource() {
        resource = new InfinispanQuarkusTestResource();
    }

    private void givenConditionalResource() {
        resource = spy(new InfinispanQuarkusTestResource.Conditional());
    }

    private void thenConditionalIsEnabled() {
        assertTrue(resource.isConditionalEnabled());
    }

    private void thenConditionalIsDisabled() {
        assertFalse(resource.isConditionalEnabled());
    }
}

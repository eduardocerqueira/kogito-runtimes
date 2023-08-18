package org.kie.kogito.testcontainers.springboot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kie.kogito.testcontainers.KogitoInfinispanContainer;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.kie.kogito.testcontainers.Constants.CONTAINER_NAME_PREFIX;
import static org.mockito.Mockito.spy;

public class InfinispanSpringBootTestResourceTest {

    private static final String IMAGE = "my-infinispan-image";

    private InfinispanSpringBootTestResource resource;

    @BeforeEach
    public void setup() {
        System.setProperty(CONTAINER_NAME_PREFIX + KogitoInfinispanContainer.NAME, IMAGE);
        givenResource();
    }

    @Test
    public void shouldGetProperty() {
        assertThrows(IllegalStateException.class, () -> resource.getProperties().get(InfinispanSpringBootTestResource.KOGITO_INFINISPAN_PROPERTY));
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
        resource = new InfinispanSpringBootTestResource();
    }

    private void givenConditionalResource() {
        resource = spy(new InfinispanSpringBootTestResource.Conditional());
    }

    private void thenConditionalIsEnabled() {
        assertTrue(resource.isConditionalEnabled());
    }

    private void thenConditionalIsDisabled() {
        assertFalse(resource.isConditionalEnabled());
    }
}

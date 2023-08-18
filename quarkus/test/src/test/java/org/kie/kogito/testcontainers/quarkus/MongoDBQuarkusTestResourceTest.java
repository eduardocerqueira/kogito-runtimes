package org.kie.kogito.testcontainers.quarkus;

import org.junit.jupiter.api.Test;
import org.kie.kogito.testcontainers.Constants;
import org.kie.kogito.testcontainers.KogitoMongoDBContainer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.spy;

class MongoDBQuarkusTestResourceTest {

    private MongoDBQuarkusTestResource resource;

    @Test
    void shouldGetProperty() {
        givenResource();
        assertThrows(IllegalStateException.class, () -> resource.getProperties().get(MongoDBQuarkusTestResource.MONGODB_CONNECTION_PROPERTY));
    }

    @Test
    void shouldConditionalBeDisabledByDefault() {
        givenResource();
        thenConditionalIsDisabled();
    }

    @Test
    void shouldConditionalBeEnabled() {
        givenConditionalResource();
        thenConditionalIsEnabled();
    }

    @Test
    void shouldGetDockerImageName() {
        givenResource();
        assertEquals(System.getProperty(Constants.CONTAINER_NAME_PREFIX + KogitoMongoDBContainer.NAME), resource.getTestResource().getDockerImageName());
    }

    private void givenConditionalResource() {
        resource = spy(new MongoDBQuarkusTestResource.Conditional());
    }

    private void givenResource() {
        resource = spy(new MongoDBQuarkusTestResource());
    }

    private void thenConditionalIsEnabled() {
        assertTrue(resource.isConditionalEnabled());
    }

    private void thenConditionalIsDisabled() {
        assertFalse(resource.isConditionalEnabled());
    }
}

package org.kie.kogito.testcontainers.springboot;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kie.kogito.testcontainers.Constants;
import org.kie.kogito.testcontainers.KogitoMongoDBContainer;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.spy;

@ExtendWith(MockitoExtension.class)
class MongoDBSpringBootTestResourceTest {

    @Mock
    private KogitoMongoDBContainer container;

    private MongoDBSpringBootTestResource resource;

    @Test
    void shouldGetProperty() {
        givenResource();
        assertThrows(IllegalStateException.class, () -> resource.getProperties().get(MongoDBSpringBootTestResource.MONGODB_CONNECTION_PROPERTY));
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
        resource = spy(new MongoDBSpringBootTestResource.Conditional());
    }

    private void givenResource() {
        resource = spy(new MongoDBSpringBootTestResource());
    }

    private void thenConditionalIsEnabled() {
        assertTrue(resource.isConditionalEnabled());
    }

    private void thenConditionalIsDisabled() {
        assertFalse(resource.isConditionalEnabled());
    }
}

package org.kie.kogito.testcontainers.quarkus;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kie.kogito.testcontainers.KogitoKeycloakContainer;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.kie.kogito.testcontainers.Constants.CONTAINER_NAME_PREFIX;
import static org.mockito.Mockito.spy;

public class KeycloakQuarkusTestResourceTest {

    private static final String IMAGE = "my-keycloak-image";

    private KeycloakQuarkusTestResource resource;

    @BeforeEach
    public void setup() {
        System.setProperty(CONTAINER_NAME_PREFIX + KogitoKeycloakContainer.NAME, IMAGE);
    }

    @Test
    public void shouldGetProperty() {
        givenResource();
        assertThrows(IllegalStateException.class, () -> resource.getProperties().get(KeycloakQuarkusTestResource.KOGITO_KEYCLOAK_PROPERTY));
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
        resource = spy(new KeycloakQuarkusTestResource.Conditional());
    }

    private void givenResource() {
        resource = spy(new KeycloakQuarkusTestResource());
    }

    private void thenConditionalIsEnabled() {
        assertTrue(resource.isConditionalEnabled());
    }

    private void thenConditionalIsDisabled() {
        assertFalse(resource.isConditionalEnabled());
    }
}

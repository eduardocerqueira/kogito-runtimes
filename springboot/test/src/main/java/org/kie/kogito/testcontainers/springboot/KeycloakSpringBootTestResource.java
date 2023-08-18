package org.kie.kogito.testcontainers.springboot;

import java.util.Map;

import org.kie.kogito.test.resources.ConditionalSpringBootTestResource;
import org.kie.kogito.testcontainers.KogitoKeycloakContainer;

import static java.util.Collections.singletonMap;

/**
 * Keycloak spring boot resource that works within the test lifecycle.
 *
 */
public class KeycloakSpringBootTestResource extends ConditionalSpringBootTestResource<KogitoKeycloakContainer> {

    public static final String KOGITO_KEYCLOAK_PROPERTY = "keycloak.auth-server-url";

    public KeycloakSpringBootTestResource() {
        super(new KogitoKeycloakContainer());
    }

    @Override
    protected Map<String, String> getProperties() {
        return singletonMap(KOGITO_KEYCLOAK_PROPERTY, String.format("http://localhost:%s", getTestResource().getMappedPort()));
    }

    public static class Conditional extends KeycloakSpringBootTestResource {

        public Conditional() {
            super();
            enableConditional();
        }
    }

}

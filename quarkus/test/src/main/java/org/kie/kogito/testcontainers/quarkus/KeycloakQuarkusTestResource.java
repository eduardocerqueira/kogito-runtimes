package org.kie.kogito.testcontainers.quarkus;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kie.kogito.test.resources.ConditionalQuarkusTestResource;
import org.kie.kogito.testcontainers.KogitoKeycloakContainer;

import static java.lang.String.format;
import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;

/**
 * Keycloak quarkus resource that works within the test lifecycle.
 */
public class KeycloakQuarkusTestResource extends ConditionalQuarkusTestResource<KogitoKeycloakContainer> {

    public static final String KOGITO_KEYCLOAK_PROPERTY = "quarkus.oidc.auth-server-url";
    public static final String KOGITO_OIDC_TENANTS = "kogito.test.tenants";

    private List<String> tenants = emptyList();

    public KeycloakQuarkusTestResource() {
        super(new KogitoKeycloakContainer());
    }

    @Override
    public void init(Map<String, String> initArgs) {
        if (initArgs.containsKey(KOGITO_OIDC_TENANTS)) {
            tenants = Arrays.stream(initArgs.getOrDefault(KOGITO_OIDC_TENANTS, "").split(",")).collect(toList());
        }
    }

    @Override
    protected Map<String, String> getProperties() {
        Map<String, String> properties = new HashMap<>();
        String url = format("http://localhost:%s/realms/kogito", getTestResource().getMappedPort());
        properties.put(KOGITO_KEYCLOAK_PROPERTY, url);
        tenants.forEach(tenant -> properties.put(format("quarkus.oidc.%s.auth-server-url", tenant), url));
        return properties;
    }

    public static class Conditional extends KeycloakQuarkusTestResource {

        public Conditional() {
            super();
            enableConditional();
        }
    }

}

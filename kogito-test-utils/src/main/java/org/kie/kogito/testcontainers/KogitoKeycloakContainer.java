package org.kie.kogito.testcontainers;

import org.kie.kogito.test.resources.TestResource;
import org.testcontainers.containers.BindMode;
import org.testcontainers.containers.wait.strategy.Wait;

/**
 * This container wraps Keycloak container
 */
public class KogitoKeycloakContainer extends KogitoGenericContainer<KogitoKeycloakContainer> implements TestResource {

    public static final String NAME = "keycloak";
    public static final String USER = "admin";
    public static final String PASSWORD = "admin";
    public static final String REALM = "kogito";
    public static final String CLIENT_ID = "kogito-app";
    public static final String CLIENT_SECRET = "secret";
    public static final int PORT = 8080;

    private static final String REALM_FILE = "/opt/keycloak/data/import/realm.json";

    public KogitoKeycloakContainer() {
        super(NAME);
        addExposedPort(PORT);
        withEnv("KEYCLOAK_ADMIN", USER);
        withEnv("KEYCLOAK_ADMIN_PASSWORD", PASSWORD);
        withClasspathResourceMapping("testcontainers/keycloak/kogito-realm.json", REALM_FILE, BindMode.READ_ONLY);
        waitingFor(Wait.forLogMessage(".*Keycloak.*started.*", 1));
        withCommand("start-dev --import-realm");
    }

    @Override
    public int getMappedPort() {
        return getMappedPort(PORT);
    }

    @Override
    public String getResourceName() {
        return NAME;
    }
}

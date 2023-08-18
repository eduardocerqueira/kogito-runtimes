package org.kie.kogito.testcontainers;

import org.kie.kogito.test.resources.TestResource;
import org.testcontainers.containers.wait.strategy.Wait;

public class KogitoRedisSearchContainer extends KogitoGenericContainer<KogitoRedisSearchContainer> implements TestResource {

    public static final String NAME = "redis";
    public static final int PORT = 6379;

    public KogitoRedisSearchContainer() {
        super(NAME);
        addExposedPort(PORT);
        waitingFor(Wait.forLogMessage(".*Ready to accept connections.*\\s", 1));
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
package org.kie.kogito.testcontainers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.output.Slf4jLogConsumer;
import org.testcontainers.utility.TestcontainersConfiguration;

import static org.kie.kogito.testcontainers.Constants.CONTAINER_START_TIMEOUT;

public abstract class KogitoGenericContainer<T extends GenericContainer<T>> extends GenericContainer<T> {

    private static final Logger LOGGER = LoggerFactory.getLogger(KogitoGenericContainer.class);

    public KogitoGenericContainer(String containerName) {
        super(getImageName(containerName));
        withStartupTimeout(CONTAINER_START_TIMEOUT);
        withLogConsumer(new Slf4jLogConsumer(LOGGER));
        withLogConsumer(f -> System.out.print(f.getUtf8String()));
    }

    public static String getImageName(String name) {
        return System.getProperty(Constants.CONTAINER_NAME_PREFIX + name,
                TestcontainersConfiguration.getInstance().getClasspathProperties().getProperty(Constants.CONTAINER_NAME_PREFIX + name));
    }
}

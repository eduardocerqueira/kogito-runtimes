package org.kie.kogito.testcontainers;

import org.kie.kogito.test.resources.TestResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.containers.output.Slf4jLogConsumer;
import org.testcontainers.utility.DockerImageName;

import static org.kie.kogito.testcontainers.KogitoGenericContainer.getImageName;

/**
 * MongoDB Container for Kogito examples.
 */
public class KogitoMongoDBContainer extends MongoDBContainer implements TestResource {

    public static final String NAME = "mongodb";
    public static final int MONGODB_INTERNAL_PORT = 27018;

    private static final Logger LOGGER = LoggerFactory.getLogger(KogitoMongoDBContainer.class);

    public KogitoMongoDBContainer() {
        super(DockerImageName.parse(getImageName(NAME)).asCompatibleSubstituteFor("mongo"));
        withLogConsumer(f -> System.out.print(f.getUtf8String()));
        withLogConsumer(new Slf4jLogConsumer(LOGGER));
        withStartupTimeout(Constants.CONTAINER_START_TIMEOUT);
    }

    @Override
    public int getMappedPort() {
        return getMappedPort(MONGODB_INTERNAL_PORT);
    }

    @Override
    public String getResourceName() {
        return NAME;
    }

}

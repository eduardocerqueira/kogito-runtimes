package org.kie.kogito.testcontainers;

import java.time.Duration;

public class Constants {

    public static final Duration CONTAINER_START_TIMEOUT = Duration.ofMinutes(15);
    public static final String CONTAINER_NAME_PREFIX = "container.image.";

    private Constants() {

    }
}

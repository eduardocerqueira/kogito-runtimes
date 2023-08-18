package org.kie.kogito.quarkus.config;

import io.quarkus.runtime.annotations.ConfigGroup;
import io.quarkus.runtime.annotations.ConfigItem;

@ConfigGroup
public class KogitoEventingRuntimeConfig {

    private static final String MAX_THREADS_PROPERTY = "threads.poolSize";
    private static final String DEFAULT_MAX_THREADS = "10";
    private static final String DEFAULT_QUEUE_SIZE = "1";
    private static final String QUEUE_SIZE_PROPERTY = "threads.queueSize";

    /**
     * Maximum number of threads to handle incoming events by channel
     */
    @ConfigItem(name = MAX_THREADS_PROPERTY, defaultValue = DEFAULT_MAX_THREADS)
    public int maxThreads;

    /**
     * Maximum size of queue to hold incoming events to be processed by threads in the pool
     */
    @ConfigItem(name = QUEUE_SIZE_PROPERTY, defaultValue = DEFAULT_QUEUE_SIZE)
    public int queueSize;
}

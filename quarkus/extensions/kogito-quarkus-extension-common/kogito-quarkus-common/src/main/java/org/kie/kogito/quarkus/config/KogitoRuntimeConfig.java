package org.kie.kogito.quarkus.config;

import java.util.Optional;

import io.quarkus.runtime.annotations.ConfigItem;
import io.quarkus.runtime.annotations.ConfigPhase;
import io.quarkus.runtime.annotations.ConfigRoot;

@ConfigRoot(name = "", phase = ConfigPhase.RUN_TIME, prefix = "kogito")
public class KogitoRuntimeConfig {

    /**
     * The service URL needed to connect to the runtime endpoint from outside the service.
     * <p>
     */
    @ConfigItem(name = "service.url")
    public Optional<String> serviceUrl;

    /**
     * Eventing runtime configuration
     */
    @ConfigItem(name = "quarkus.events")
    public KogitoEventingRuntimeConfig eventingConfig;

    /**
     * Maximum number of process instance to be returned by GET api
     */
    @ConfigItem(name = "process.instances.limit", defaultValue = "1000")
    public short processInstanceLimit;
}

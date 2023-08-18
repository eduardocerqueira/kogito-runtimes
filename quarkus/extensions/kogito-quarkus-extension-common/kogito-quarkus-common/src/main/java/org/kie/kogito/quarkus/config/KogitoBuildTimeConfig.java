package org.kie.kogito.quarkus.config;

import io.quarkus.runtime.annotations.ConfigItem;
import io.quarkus.runtime.annotations.ConfigPhase;
import io.quarkus.runtime.annotations.ConfigRoot;

@ConfigRoot(name = "", phase = ConfigPhase.BUILD_AND_RUN_TIME_FIXED, prefix = "kogito")
public class KogitoBuildTimeConfig {

    /**
     * If this is enabled the service will use Cloud Events
     * <p>
     * If not defined, true will be used.
     */
    @ConfigItem(name = "messaging.as-cloudevents", defaultValue = "true")
    public Boolean useCloudEvents;

    /**
     * If this property is True, Jackson will fail on an empty bean
     * <p>
     * If not defined, false will be used.
     */
    @ConfigItem(name = "jackson.fail-on-empty-bean", defaultValue = "false")
    public Boolean failOnEmptyBean;

    /**
     * Incoming channel default name
     */
    @ConfigItem(name = "addon.messaging.incoming.defaultName", defaultValue = "kogito_incoming_stream")
    public String incomingChannelDefaultName;

    /**
     * Outgoing channel default name
     */
    @ConfigItem(name = "addon.messaging.outgoing.defaultName", defaultValue = "kogito_outgoing_stream")
    public String outgoingChannelDefaultName;

    /**
     * Whether to ignore hidden files when collecting files for code generation
     * <p>
     * If not defined, true will be used.
     */
    @ConfigItem(name = "codegen.ignoreHiddenFiles", defaultValue = "true")
    public Boolean ignoreHiddenFiles;

    /**
     * Whether to fail when there are parsing/validation errors of process assets
     * <p>
     * If not defined, true will be used.
     */
    @ConfigItem(name = "codegen.process.failOnError", defaultValue = "true")
    public Boolean failOnError;
}

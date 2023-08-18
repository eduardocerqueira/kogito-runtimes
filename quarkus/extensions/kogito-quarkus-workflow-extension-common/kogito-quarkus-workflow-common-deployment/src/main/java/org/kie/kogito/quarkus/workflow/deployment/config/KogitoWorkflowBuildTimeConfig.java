package org.kie.kogito.quarkus.workflow.deployment.config;

import io.quarkus.runtime.annotations.ConfigItem;
import io.quarkus.runtime.annotations.ConfigPhase;
import io.quarkus.runtime.annotations.ConfigRoot;

@ConfigRoot(name = "kogito", phase = ConfigPhase.BUILD_TIME)
public class KogitoWorkflowBuildTimeConfig {

    /**
     * Configuration for DevServices. DevServices allows Quarkus to automatically start Data Index in dev and test mode.
     */
    @ConfigItem
    public KogitoDevServicesBuildTimeConfig devservices;

    /**
     * Always include the Workflow debug logger. By default, this will only be included in dev and test.
     * Setting this to true will also include the Workflow debug logger in Prod
     */
    @ConfigItem(name = "logger.always-include", defaultValue = "false")
    public boolean alwaysInclude;

}

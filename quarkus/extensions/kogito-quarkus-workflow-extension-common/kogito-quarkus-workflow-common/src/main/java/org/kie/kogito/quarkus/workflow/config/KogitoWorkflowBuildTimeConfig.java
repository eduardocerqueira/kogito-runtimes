package org.kie.kogito.quarkus.workflow.config;

import io.quarkus.runtime.annotations.ConfigItem;
import io.quarkus.runtime.annotations.ConfigPhase;
import io.quarkus.runtime.annotations.ConfigRoot;

@ConfigRoot(name = "", phase = ConfigPhase.BUILD_AND_RUN_TIME_FIXED, prefix = "kogito")
public class KogitoWorkflowBuildTimeConfig {

    /**
     * Strategy to resolve a Process version to use
     */
    @ConfigItem(name = "workflow.version-strategy", defaultValue = "workflow")
    public String versionStrategy;

    /**
     * Persistence build time configuration
     */
    @ConfigItem
    public KogitoPersistenceBuildTimeConfig persistence;

}

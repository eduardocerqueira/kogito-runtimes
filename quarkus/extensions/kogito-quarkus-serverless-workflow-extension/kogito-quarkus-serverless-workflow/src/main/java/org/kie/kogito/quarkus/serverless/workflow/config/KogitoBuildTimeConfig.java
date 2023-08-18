package org.kie.kogito.quarkus.serverless.workflow.config;

import io.quarkus.runtime.annotations.ConfigItem;
import io.quarkus.runtime.annotations.ConfigPhase;
import io.quarkus.runtime.annotations.ConfigRoot;

@ConfigRoot(name = "", phase = ConfigPhase.BUILD_AND_RUN_TIME_FIXED, prefix = "kogito")
public class KogitoBuildTimeConfig {

    /**
     * Serverless Workflow build time configuration
     */
    @ConfigItem
    public KogitoServerlessWorkflowBuildTimeConfig sw;
}

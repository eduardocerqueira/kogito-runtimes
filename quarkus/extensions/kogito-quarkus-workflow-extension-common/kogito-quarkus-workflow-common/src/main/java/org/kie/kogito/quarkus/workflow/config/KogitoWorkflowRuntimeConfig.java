package org.kie.kogito.quarkus.workflow.config;

import io.quarkus.runtime.annotations.ConfigItem;
import io.quarkus.runtime.annotations.ConfigPhase;
import io.quarkus.runtime.annotations.ConfigRoot;

@ConfigRoot(name = "", phase = ConfigPhase.RUN_TIME, prefix = "kogito")
public class KogitoWorkflowRuntimeConfig {

    /**
     * Persistence runtime configuration
     */
    @ConfigItem
    public KogitoPersistenceRuntimeConfig persistence;

}

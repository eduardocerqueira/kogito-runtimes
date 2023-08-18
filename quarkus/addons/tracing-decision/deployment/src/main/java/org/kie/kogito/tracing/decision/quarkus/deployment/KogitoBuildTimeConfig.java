package org.kie.kogito.tracing.decision.quarkus.deployment;

import io.quarkus.runtime.annotations.ConfigItem;
import io.quarkus.runtime.annotations.ConfigPhase;
import io.quarkus.runtime.annotations.ConfigRoot;

@ConfigRoot(name = "kogito", phase = ConfigPhase.BUILD_TIME)
public class KogitoBuildTimeConfig {

    /**
     * Configuration for DevServices. DevServices allows Quarkus to automatically start TrustyService in dev and test mode.
     */
    @ConfigItem
    public KogitoDevServicesBuildTimeConfig devServicesTrusty;
}

package org.kie.kogito.quarkus.serverless.workflow.config;

import io.quarkus.runtime.annotations.ConfigItem;
import io.quarkus.runtime.annotations.ConfigPhase;
import io.quarkus.runtime.annotations.ConfigRoot;

@ConfigRoot(name = "", phase = ConfigPhase.RUN_TIME, prefix = "kogito")
public class KogitoServerlessWorkflowRuntimeConfig {

    /**
     * grpc runtime configuration
     */
    @ConfigItem
    public KogitoRPCRuntimeConfig grpc;

}

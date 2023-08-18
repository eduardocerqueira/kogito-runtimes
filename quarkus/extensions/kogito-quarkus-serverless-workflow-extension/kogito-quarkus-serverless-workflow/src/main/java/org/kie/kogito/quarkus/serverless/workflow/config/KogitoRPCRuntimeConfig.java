package org.kie.kogito.quarkus.serverless.workflow.config;

import io.quarkus.runtime.annotations.ConfigGroup;
import io.quarkus.runtime.annotations.ConfigItem;

@ConfigGroup
public class KogitoRPCRuntimeConfig {

    /**
     * Indicates if default value of enumerations should be included in grpc response
     * 
     */
    @ConfigItem(name = "enum.includeDefault", defaultValue = "false")
    public boolean enumDefault;

    /**
     * Time to wait for response from server when using streams
     * 
     */
    @ConfigItem(name = "stream.timeout", defaultValue = "20")
    public int streamTimeout;
}

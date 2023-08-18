package org.kie.kogito.quarkus.workflow.config;

import io.quarkus.runtime.annotations.ConfigGroup;
import io.quarkus.runtime.annotations.ConfigItem;

@ConfigGroup
public class KogitoPersistenceBuildTimeConfig {

    /**
     * Generate Protobuf marshallers for runtime
     */
    @ConfigItem(name = "proto.marshaller", defaultValue = "true")
    public boolean runtimeProtoMarshaller;

    /**
     * Generate Protobuf marshallers for Data Index
     */
    @ConfigItem(name = "data-index.proto.generation", defaultValue = "true")
    public boolean dataIndexProtoMarshaller;

}

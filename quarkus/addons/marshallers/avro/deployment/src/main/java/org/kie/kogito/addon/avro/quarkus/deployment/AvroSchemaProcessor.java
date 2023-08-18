package org.kie.kogito.addon.avro.quarkus.deployment;

import org.kie.kogito.event.avro.AvroIO;

import io.quarkus.deployment.annotations.BuildProducer;
import io.quarkus.deployment.annotations.BuildStep;
import io.quarkus.deployment.builditem.nativeimage.NativeImageResourceBuildItem;
import io.quarkus.deployment.pkg.steps.NativeOrNativeSourcesBuild;

public class AvroSchemaProcessor {

    @BuildStep(onlyIf = NativeOrNativeSourcesBuild.class)
    void addAvroSchema(BuildProducer<NativeImageResourceBuildItem> resource) {
        resource.produce(new NativeImageResourceBuildItem(AvroIO.CLOUD_EVENT_SCHEMA_NAME));
        resource.produce(new NativeImageResourceBuildItem(AvroIO.JSON_NODE_SCHEMA_NAME));
    }
}

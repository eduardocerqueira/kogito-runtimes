package org.kie.kogito.event.avro;

import java.io.IOException;
import java.util.function.Function;

import org.kie.kogito.event.CloudEventMarshaller;

import io.cloudevents.CloudEvent;
import io.cloudevents.CloudEventData;

public class AvroCloudEventMarshaller implements CloudEventMarshaller<byte[]> {

    private final AvroIO avroUtils;

    public AvroCloudEventMarshaller(AvroIO avroUtils) {
        this.avroUtils = avroUtils;
    }

    @Override
    public byte[] marshall(CloudEvent event) throws IOException {
        return avroUtils.writeCloudEvent(event);
    }

    @Override
    public <T> Function<T, CloudEventData> cloudEventDataFactory() {
        return new AvroCloudEventDataFactory<>(avroUtils);
    }
}

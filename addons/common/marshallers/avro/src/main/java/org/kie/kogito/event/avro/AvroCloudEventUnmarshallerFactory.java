package org.kie.kogito.event.avro;

import org.kie.kogito.event.CloudEventUnmarshaller;
import org.kie.kogito.event.CloudEventUnmarshallerFactory;
import org.kie.kogito.event.impl.DefaultCloudEventUnmarshaller;

import io.cloudevents.CloudEventData;
import io.cloudevents.core.data.BytesCloudEventData;

public class AvroCloudEventUnmarshallerFactory implements CloudEventUnmarshallerFactory<byte[]> {

    private final AvroIO avroUtils;

    public AvroCloudEventUnmarshallerFactory(AvroIO avroUtils) {
        this.avroUtils = avroUtils;
    }

    @Override
    public <S> CloudEventUnmarshaller<byte[], S> unmarshaller(Class<S> targetClass) {
        return new DefaultCloudEventUnmarshaller<>(avroUtils::readCloudEvent, new AvroCloudEventDataConverter<>(avroUtils, targetClass), this::getCloudEventData);
    }

    private CloudEventData getCloudEventData(byte[] bytes) {
        return bytes == null ? null : BytesCloudEventData.wrap(bytes);
    }
}

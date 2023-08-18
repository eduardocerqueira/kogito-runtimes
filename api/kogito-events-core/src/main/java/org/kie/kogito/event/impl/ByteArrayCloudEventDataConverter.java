package org.kie.kogito.event.impl;

import java.io.IOException;

import org.kie.kogito.event.Converter;

import io.cloudevents.CloudEventData;
import io.cloudevents.core.data.BytesCloudEventData;

public class ByteArrayCloudEventDataConverter implements Converter<byte[], CloudEventData> {
    @Override
    public CloudEventData convert(byte[] value) throws IOException {
        return value == null ? null : BytesCloudEventData.wrap(value);
    }
}

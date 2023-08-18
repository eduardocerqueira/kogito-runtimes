package org.kie.kogito.event.impl;

import java.io.IOException;

import org.kie.kogito.event.Converter;

import io.cloudevents.CloudEventData;
import io.cloudevents.core.data.BytesCloudEventData;

public class ObjectCloudEventDataConverter implements Converter<Object, CloudEventData> {
    @Override
    public CloudEventData convert(Object value) throws IOException {
        if (value instanceof CloudEventData) {
            return (CloudEventData) value;
        }
        return value == null ? null : BytesCloudEventData.wrap(value.toString().getBytes());
    }
}

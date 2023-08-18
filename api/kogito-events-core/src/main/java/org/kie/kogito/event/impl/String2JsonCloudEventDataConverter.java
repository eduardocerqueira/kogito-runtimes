package org.kie.kogito.event.impl;

import java.io.IOException;

import org.kie.kogito.event.Converter;

import io.cloudevents.CloudEventData;
import io.cloudevents.core.data.BytesCloudEventData;

public class String2JsonCloudEventDataConverter implements Converter<String, CloudEventData> {
    @Override
    public CloudEventData convert(String value) throws IOException {
        return value == null ? null : BytesCloudEventData.wrap(value.getBytes());
    }
}

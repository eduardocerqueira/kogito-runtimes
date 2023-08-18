package org.kie.kogito.event.impl;

import java.io.IOException;

import org.kie.kogito.event.Converter;

import io.cloudevents.CloudEvent;

public abstract class AbstractCloudEventConverter<I> implements Converter<I, CloudEvent> {
    @Override
    public CloudEvent convert(I value) throws IOException {
        if (value == null) {
            return null;
        } else if (value instanceof CloudEvent) {
            return (CloudEvent) value;
        } else {
            return toValue(value);
        }
    }

    protected abstract CloudEvent toValue(I value) throws IOException;
}

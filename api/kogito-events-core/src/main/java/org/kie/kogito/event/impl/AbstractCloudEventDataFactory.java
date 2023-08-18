package org.kie.kogito.event.impl;

import java.io.IOException;
import java.util.function.Function;

import org.kie.kogito.event.cloudevents.utils.CloudEventUtils;

import io.cloudevents.CloudEventData;

public abstract class AbstractCloudEventDataFactory<T> implements Function<T, CloudEventData> {

    @Override
    public CloudEventData apply(T event) {
        return CloudEventUtils.fromObject(event, this::toBytes);
    }

    protected abstract byte[] toBytes(T event) throws IOException;

}

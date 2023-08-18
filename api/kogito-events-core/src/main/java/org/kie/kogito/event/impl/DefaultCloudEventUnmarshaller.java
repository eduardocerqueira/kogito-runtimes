package org.kie.kogito.event.impl;

import org.kie.kogito.event.CloudEventUnmarshaller;
import org.kie.kogito.event.Converter;

import io.cloudevents.CloudEvent;
import io.cloudevents.CloudEventData;

public class DefaultCloudEventUnmarshaller<I, O> implements CloudEventUnmarshaller<I, O> {

    private Converter<I, CloudEvent> cloudEventConverter;
    private Converter<I, CloudEventData> cloudEventDataConverter;
    private Converter<CloudEventData, O> dataConverter;

    public DefaultCloudEventUnmarshaller(Converter<I, CloudEvent> cloudEventConverter, Converter<CloudEventData, O> dataConverter, Converter<I, CloudEventData> cloudEventDataConverter) {
        this.dataConverter = dataConverter;
        this.cloudEventConverter = cloudEventConverter;
        this.cloudEventDataConverter = cloudEventDataConverter;
    }

    @Override
    public Converter<I, CloudEvent> cloudEvent() {
        return cloudEventConverter;
    }

    @Override
    public Converter<CloudEventData, O> data() {
        return dataConverter;
    }

    @Override
    public Converter<I, CloudEventData> binaryCloudEvent() {
        return cloudEventDataConverter;
    }
}

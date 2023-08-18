package org.kie.kogito.event.impl;

import java.io.IOException;

import org.kie.kogito.event.CloudEventUnmarshaller;
import org.kie.kogito.event.CloudEventUnmarshallerFactory;
import org.kie.kogito.event.Converter;
import org.kie.kogito.event.DataEvent;
import org.kie.kogito.event.DataEventFactory;

public class CloudEventConverter<T, S> implements Converter<T, DataEvent<S>> {

    private final CloudEventUnmarshaller<T, S> unmarshaller;

    public CloudEventConverter(Class<S> objectClass, CloudEventUnmarshallerFactory<T> factory) {
        this.unmarshaller = factory.unmarshaller(objectClass);
    }

    @Override
    public DataEvent<S> convert(T value) throws IOException {
        return DataEventFactory.from(unmarshaller.cloudEvent().convert(value), unmarshaller.data());
    }

}

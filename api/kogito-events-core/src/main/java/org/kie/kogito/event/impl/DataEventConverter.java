package org.kie.kogito.event.impl;

import java.io.IOException;

import org.kie.kogito.event.Converter;
import org.kie.kogito.event.DataEvent;
import org.kie.kogito.event.DataEventFactory;
import org.kie.kogito.event.EventUnmarshaller;

public class DataEventConverter<T, S> implements Converter<T, DataEvent<S>> {

    private final Class<S> objectClass;
    private final EventUnmarshaller<T> unmarshaller;

    public DataEventConverter(Class<S> objectClass, EventUnmarshaller<T> unmarshaller) {
        this.objectClass = objectClass;
        this.unmarshaller = unmarshaller;
    }

    @Override
    public DataEvent<S> convert(T value) throws IOException {
        return DataEventFactory.from(unmarshaller.unmarshall(value, objectClass));
    }
}

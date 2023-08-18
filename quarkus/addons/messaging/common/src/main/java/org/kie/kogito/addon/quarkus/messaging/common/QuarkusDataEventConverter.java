package org.kie.kogito.addon.quarkus.messaging.common;

import java.io.IOException;

import org.eclipse.microprofile.reactive.messaging.Message;
import org.kie.kogito.event.Converter;
import org.kie.kogito.event.DataEvent;
import org.kie.kogito.event.DataEventFactory;
import org.kie.kogito.event.EventUnmarshaller;

public class QuarkusDataEventConverter<I, T> implements
        Converter<Message<I>, DataEvent<T>> {

    private final Class<T> objectClass;
    private final EventUnmarshaller<I> unmarshaller;

    public QuarkusDataEventConverter(Class<T> objectClass, EventUnmarshaller<I> unmarshaller) {
        this.objectClass = objectClass;
        this.unmarshaller = unmarshaller;
    }

    @Override
    public DataEvent<T> convert(Message<I> value) throws IOException {
        return DataEventFactory.from(unmarshaller.unmarshall(value.getPayload(), objectClass));
    }
}

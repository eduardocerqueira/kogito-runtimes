package org.kie.kogito.addon.quarkus.messaging.common;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import org.kie.kogito.event.CloudEventMarshaller;
import org.kie.kogito.event.EventMarshaller;
import org.kie.kogito.event.impl.ByteArrayCloudEventMarshaller;
import org.kie.kogito.event.impl.ByteArrayEventMarshaller;
import org.kie.kogito.event.impl.NoOpCloudEventMarshaller;
import org.kie.kogito.event.impl.NoOpEventMarshaller;
import org.kie.kogito.event.impl.StringCloudEventMarshaller;
import org.kie.kogito.event.impl.StringEventMarshaller;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.quarkus.arc.DefaultBean;

@ApplicationScoped
public class EventMarshallerProducer {

    @Inject
    ObjectMapper mapper;

    @Produces
    @DefaultBean
    public EventMarshaller<String> stringEventMarshaller() {
        return new StringEventMarshaller(mapper);
    }

    @Produces
    @DefaultBean
    public EventMarshaller<byte[]> byteArrayEventMarshaller() {
        return new ByteArrayEventMarshaller(mapper);
    }

    @Produces
    @DefaultBean
    public EventMarshaller<Object> defaultEventMarshaller() {
        return new NoOpEventMarshaller();
    }

    @Produces
    @DefaultBean
    public CloudEventMarshaller<String> stringCloudEventMarshaller() {
        return new StringCloudEventMarshaller(mapper);
    }

    @Produces
    @DefaultBean
    public CloudEventMarshaller<byte[]> byteArrayCloudEventMarshaller() {
        return new ByteArrayCloudEventMarshaller(mapper);
    }

    @Produces
    @DefaultBean
    public CloudEventMarshaller<Object> defaultCloudEventMarshaller() {
        return new NoOpCloudEventMarshaller(mapper);
    }
}

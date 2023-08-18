package org.kie.kogito.addon.quarkus.messaging.common;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import org.kie.kogito.event.CloudEventUnmarshallerFactory;
import org.kie.kogito.event.EventUnmarshaller;
import org.kie.kogito.event.impl.ByteArrayCloudEventUnmarshallerFactory;
import org.kie.kogito.event.impl.JacksonEventDataUnmarshaller;
import org.kie.kogito.event.impl.ObjectCloudEventUnmarshallerFactory;
import org.kie.kogito.event.impl.StringCloudEventUnmarshallerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.quarkus.arc.DefaultBean;

@ApplicationScoped
public class EventUnmarshallerProducer {

    @Inject
    ObjectMapper objectMapper;

    @Produces
    @DefaultBean
    public EventUnmarshaller<Object> objectEventDataConverter() {
        return new JacksonEventDataUnmarshaller<>(objectMapper);
    }

    @Produces
    @DefaultBean
    public EventUnmarshaller<String> stringEventDataConverter() {
        return new JacksonEventDataUnmarshaller<>(objectMapper);
    }

    @Produces
    @DefaultBean
    public EventUnmarshaller<byte[]> bytesEventDataConverter() {
        return new JacksonEventDataUnmarshaller<>(objectMapper);
    }

    @Produces
    @DefaultBean
    public CloudEventUnmarshallerFactory<Object> objectCloudEventConverter() {
        return new ObjectCloudEventUnmarshallerFactory(objectMapper);
    }

    @Produces
    @DefaultBean
    public CloudEventUnmarshallerFactory<String> stringCloudEventConverter() {
        return new StringCloudEventUnmarshallerFactory(objectMapper);
    }

    @Produces
    @DefaultBean
    public CloudEventUnmarshallerFactory<byte[]> bytesCloudEventConverter() {
        return new ByteArrayCloudEventUnmarshallerFactory(objectMapper);
    }
}

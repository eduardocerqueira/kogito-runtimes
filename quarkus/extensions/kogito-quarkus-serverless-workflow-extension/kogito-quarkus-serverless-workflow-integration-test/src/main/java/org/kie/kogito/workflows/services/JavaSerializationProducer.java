package org.kie.kogito.workflows.services;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import org.kie.kogito.addon.quarkus.messaging.common.ChannelFormat;
import org.kie.kogito.event.CloudEventUnmarshallerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

@ApplicationScoped
public class JavaSerializationProducer {

    @Inject
    ObjectMapper objectMapper;

    @Produces
    @Named("java")
    @ChannelFormat
    public CloudEventUnmarshallerFactory<Object> getJavaCloudEventUnmarshallerFactory() {
        return new JavaSerializationUnmarshallerFactory(objectMapper);
    }

}

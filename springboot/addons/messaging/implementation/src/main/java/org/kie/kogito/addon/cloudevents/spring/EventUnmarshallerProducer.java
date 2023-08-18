package org.kie.kogito.addon.cloudevents.spring;

import org.kie.kogito.event.CloudEventUnmarshallerFactory;
import org.kie.kogito.event.EventUnmarshaller;
import org.kie.kogito.event.impl.JacksonEventDataUnmarshaller;
import org.kie.kogito.event.impl.ObjectCloudEventUnmarshallerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class EventUnmarshallerProducer {

    @Autowired
    ObjectMapper objectMapper;

    @Bean
    public EventUnmarshaller<Object> getEventDataConverter() {
        return new JacksonEventDataUnmarshaller<>(objectMapper);
    }

    @Bean
    public CloudEventUnmarshallerFactory<Object> getCloudEventConverter() {
        return new ObjectCloudEventUnmarshallerFactory(objectMapper);
    }
}

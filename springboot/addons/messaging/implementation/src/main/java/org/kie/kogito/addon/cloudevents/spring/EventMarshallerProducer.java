package org.kie.kogito.addon.cloudevents.spring;

import org.kie.kogito.event.CloudEventMarshaller;
import org.kie.kogito.event.EventMarshaller;
import org.kie.kogito.event.impl.StringCloudEventMarshaller;
import org.kie.kogito.event.impl.StringEventMarshaller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class EventMarshallerProducer {

    @Autowired
    ObjectMapper mapper;

    @Bean
    public EventMarshaller<String> stringEventMarshaller() {
        return new StringEventMarshaller(mapper);
    }

    @Bean
    public CloudEventMarshaller<String> stringCloudEventMarshaller() {
        return new StringCloudEventMarshaller(mapper);
    }
}

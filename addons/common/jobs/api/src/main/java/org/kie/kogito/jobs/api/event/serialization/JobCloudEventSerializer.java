package org.kie.kogito.jobs.api.event.serialization;

import org.kie.kogito.jobs.api.event.JobCloudEvent;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import io.cloudevents.jackson.JsonFormat;

@Deprecated
public class JobCloudEventSerializer {

    /**
     * Shared default object mapper with the minimal required setups that can be used by the JobCloudEventDeserializer
     * and JobCloudEventSerializer classes. Interested parties that doesn't want to use this object mapper can use
     * their own provided mappers by using the proper constructors.
     */
    public static final ObjectMapper DEFAULT_OBJECT_MAPPER = new ObjectMapper()
            .registerModule(new JavaTimeModule())
            .registerModule(JsonFormat.getCloudEventJacksonModule())
            .disable(com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

    private final ObjectMapper objectMapper;

    public JobCloudEventSerializer() {
        this.objectMapper = DEFAULT_OBJECT_MAPPER;
    }

    public JobCloudEventSerializer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public String serialize(JobCloudEvent<?> jobCloudEvent) {
        try {
            return objectMapper.writeValueAsString(jobCloudEvent);
        } catch (JsonProcessingException e) {
            throw new SerializationException("An error was produced during a JobCloudEvent serialization: " + e.getMessage(), e);
        }
    }
}

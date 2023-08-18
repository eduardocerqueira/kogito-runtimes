package org.kie.kogito.jobs.service.api.event.serialization;

import java.io.IOException;
import java.util.Objects;

import org.kie.kogito.jobs.service.api.Job;
import org.kie.kogito.jobs.service.api.JobLookupId;
import org.kie.kogito.jobs.service.api.event.CreateJobEvent;
import org.kie.kogito.jobs.service.api.event.DeleteJobEvent;
import org.kie.kogito.jobs.service.api.event.JobCloudEvent;
import org.kie.kogito.jobs.service.api.serlialization.DeserializationException;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.cloudevents.CloudEvent;
import io.cloudevents.CloudEventData;

import static org.kie.kogito.jobs.service.api.serlialization.SerializationUtils.DEFAULT_OBJECT_MAPPER;
import static org.kie.kogito.jobs.service.api.serlialization.SerializationUtils.registerDescriptors;

public class JobCloudEventDeserializer {

    private final ObjectMapper objectMapper;

    public JobCloudEventDeserializer() {
        this.objectMapper = DEFAULT_OBJECT_MAPPER;
    }

    public JobCloudEventDeserializer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        registerDescriptors(objectMapper);
    }

    public JobCloudEvent<?> deserialize(byte[] data) {
        try {
            CloudEvent cloudEvent = objectMapper.readValue(data, CloudEvent.class);
            return deserialize(cloudEvent);
        } catch (IOException e) {
            throw new DeserializationException("An error was produced during a JobCloudEvent deserialization: " + e.getMessage(), e);
        }
    }

    public JobCloudEvent<?> deserialize(CloudEvent cloudEvent) {
        try {
            if (cloudEvent.getSpecVersion() != JobCloudEvent.SPEC_VERSION) {
                throw new DeserializationException("JobCloudEvents are expected to be in the CloudEvent specversion: " + JobCloudEvent.SPEC_VERSION);
            }
            CloudEventData cloudEventData = Objects.requireNonNull(cloudEvent.getData(), "JobCloudEvent data field must not be null");
            if (cloudEvent.getType().equals(CreateJobEvent.TYPE)) {
                Job job = objectMapper.readValue(cloudEventData.toBytes(), Job.class);
                return CreateJobEvent.builder()
                        .withValuesFrom(cloudEvent)
                        .job(job)
                        .build();
            } else if (cloudEvent.getType().equals(DeleteJobEvent.TYPE)) {
                JobLookupId lookupId = objectMapper.readValue(cloudEventData.toBytes(), JobLookupId.class);
                return DeleteJobEvent.builder()
                        .withValuesFrom(cloudEvent)
                        .lookupId(lookupId)
                        .build();
            }
            throw new DeserializationException("Unknown JobCloudEvent event type: " + cloudEvent.getType());
        } catch (IOException e) {
            throw new DeserializationException("An error was produced during a JobCloudEvent deserialization: " + e.getMessage(), e);
        }
    }
}

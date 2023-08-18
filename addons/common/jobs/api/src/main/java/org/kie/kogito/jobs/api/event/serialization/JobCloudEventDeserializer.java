package org.kie.kogito.jobs.api.event.serialization;

import java.io.IOException;
import java.util.Objects;

import org.kie.kogito.jobs.api.Job;
import org.kie.kogito.jobs.api.event.CancelJobRequestEvent;
import org.kie.kogito.jobs.api.event.CreateProcessInstanceJobRequestEvent;
import org.kie.kogito.jobs.api.event.JobCloudEvent;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.cloudevents.CloudEvent;
import io.cloudevents.CloudEventData;

import static org.kie.kogito.jobs.api.event.serialization.JobCloudEventSerializer.DEFAULT_OBJECT_MAPPER;

@Deprecated
public class JobCloudEventDeserializer {

    private final ObjectMapper objectMapper;

    public JobCloudEventDeserializer() {
        this.objectMapper = DEFAULT_OBJECT_MAPPER;
    }

    public JobCloudEventDeserializer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
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
            CloudEventData cloudEventData = Objects.requireNonNull(cloudEvent.getData(), "JobCloudEvent data field must not be null");
            if (cloudEvent.getType().equals(CreateProcessInstanceJobRequestEvent.CREATE_PROCESS_INSTANCE_JOB_REQUEST)) {
                Job job = objectMapper.readValue(cloudEventData.toBytes(), Job.class);
                return CreateProcessInstanceJobRequestEvent.builder()
                        .withValuesFrom(cloudEvent)
                        .withContextFrom(cloudEvent)
                        .job(job)
                        .build();
            } else if (cloudEvent.getType().equals(CancelJobRequestEvent.CANCEL_JOB_REQUEST)) {
                CancelJobRequestEvent.JobId jobId = objectMapper.readValue(cloudEventData.toBytes(), CancelJobRequestEvent.JobId.class);
                return CancelJobRequestEvent.builder()
                        .withValuesFrom(cloudEvent)
                        .withContextFrom(cloudEvent)
                        .jobId(jobId.getId())
                        .build();
            }
            throw new DeserializationException("Unknown JobCloudEvent event type: " + cloudEvent.getType());
        } catch (IOException e) {
            throw new DeserializationException("An error was produced during a JobCloudEvent deserialization: " + e.getMessage(), e);
        }
    }
}

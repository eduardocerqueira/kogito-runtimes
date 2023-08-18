package org.kie.kogito.jobs.service.api;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import static org.kie.kogito.jobs.service.api.JobLookupId.CORRELATION_ID_PROPERTY;
import static org.kie.kogito.jobs.service.api.JobLookupId.ID_PROPERTY;

@Schema(description = "Logical identifier for executing job queries.")
@JsonPropertyOrder({ ID_PROPERTY, CORRELATION_ID_PROPERTY })
public class JobLookupId {

    static final String ID_PROPERTY = "id";
    static final String CORRELATION_ID_PROPERTY = "correlationId";

    private String id;
    private String correlationId;

    private JobLookupId() {
        // Marshalling constructor.
    }

    private JobLookupId(String id, String correlationId) {
        this.id = id;
        this.correlationId = correlationId;
    }

    public String getId() {
        return id;
    }

    public String getCorrelationId() {
        return correlationId;
    }

    public static JobLookupId fromId(String id) {
        return new JobLookupId(id, null);
    }

    public static JobLookupId fromCorrelationId(String correlationId) {
        return new JobLookupId(null, correlationId);
    }
}

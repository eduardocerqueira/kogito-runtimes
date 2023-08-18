package org.kie.kogito.jobs.api;

public class JobCallbackPayload {
    private String correlationId;

    public JobCallbackPayload() {
        // marshalling constructor.
    }

    public JobCallbackPayload(String correlationId) {
        this.correlationId = correlationId;
    }

    public String getCorrelationId() {
        return correlationId;
    }

    public void setCorrelationId(String correlationId) {
        this.correlationId = correlationId;
    }

    @Override
    public String toString() {
        return "JobCallbackPayload{" +
                "correlationId='" + correlationId + '\'' +
                '}';
    }
}

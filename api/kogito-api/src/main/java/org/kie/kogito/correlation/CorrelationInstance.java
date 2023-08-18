package org.kie.kogito.correlation;

public class CorrelationInstance {

    private String correlationId;// encoded based on correlations
    private String correlatedId;// == processInstanceId
    private Correlation<?> correlation;

    public CorrelationInstance(String correlationId, String correlatedId, Correlation<?> correlation) {
        this.correlationId = correlationId;
        this.correlatedId = correlatedId;
        this.correlation = correlation;
    }

    public String getCorrelationId() {
        return correlationId;
    }

    public String getCorrelatedId() {
        return correlatedId;
    }

    public Correlation<?> getCorrelation() {
        return correlation;
    }
}

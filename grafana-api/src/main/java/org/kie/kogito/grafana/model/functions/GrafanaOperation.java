package org.kie.kogito.grafana.model.functions;

public enum GrafanaOperation {
    DIVISION("/");

    private String stringValue;

    GrafanaOperation(String s) {
        this.stringValue = s;
    }

    @Override
    public String toString() {
        return stringValue;
    }
}

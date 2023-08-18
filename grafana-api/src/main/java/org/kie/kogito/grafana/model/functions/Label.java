package org.kie.kogito.grafana.model.functions;

public class Label {
    private String key;
    private String value;

    public Label(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public String render() {
        return String.format("%s=%s", key, value);
    }
}

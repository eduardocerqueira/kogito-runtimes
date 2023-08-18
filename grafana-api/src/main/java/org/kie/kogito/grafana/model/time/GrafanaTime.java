package org.kie.kogito.grafana.model.time;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GrafanaTime {

    @JsonProperty("from")
    public String from = "now-6h";

    @JsonProperty("to")
    public String to = "now";
}

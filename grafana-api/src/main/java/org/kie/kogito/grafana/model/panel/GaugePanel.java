package org.kie.kogito.grafana.model.panel;

import java.util.List;

import org.kie.kogito.grafana.model.panel.common.Options;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GaugePanel extends GrafanaPanel {

    @JsonProperty("options")
    public Options options;

    public GaugePanel() {
        super();
    }

    public GaugePanel(int id, String title, GrafanaGridPos gridPos, List<GrafanaTarget> targets) {
        super(id, title, "gauge", gridPos, targets);
    }
}

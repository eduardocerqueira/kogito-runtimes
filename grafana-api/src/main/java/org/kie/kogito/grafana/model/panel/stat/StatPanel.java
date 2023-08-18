package org.kie.kogito.grafana.model.panel.stat;

import java.util.List;

import org.kie.kogito.grafana.model.panel.GrafanaGridPos;
import org.kie.kogito.grafana.model.panel.GrafanaPanel;
import org.kie.kogito.grafana.model.panel.GrafanaTarget;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StatPanel extends GrafanaPanel {

    @JsonProperty("options")
    public StatOptions options = new StatOptions();

    public StatPanel() {
        super();
    }

    public StatPanel(int id, String title, GrafanaGridPos gridPos, List<GrafanaTarget> targets) {
        super(id, title, "stat", gridPos, targets);
    }
}

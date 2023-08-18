package org.kie.kogito.grafana.model.panel.stat;

import java.util.List;

import org.kie.kogito.grafana.model.panel.GrafanaGridPos;
import org.kie.kogito.grafana.model.panel.GrafanaPanel;
import org.kie.kogito.grafana.model.panel.GrafanaTarget;
import org.kie.kogito.grafana.model.panel.common.Options;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SingleStatPanel extends GrafanaPanel {

    @JsonProperty("options")
    public Options options;

    public SingleStatPanel() {
        super();
    }

    public SingleStatPanel(int id, String title, GrafanaGridPos gridPos, List<GrafanaTarget> targets) {
        super(id, title, "singleStat", gridPos, targets);
    }
}

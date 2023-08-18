package org.kie.kogito.grafana.factories;

import org.kie.kogito.grafana.model.panel.GrafanaGridPos;

public class GridPosFactory {

    private GridPosFactory() {
        // Intentionally left blank.
    }

    public static GrafanaGridPos calculateGridPosById(int id) {
        return new GrafanaGridPos(12 * ((id - 1) % 2), 8 * ((id - 1) / 2), 12, 8);
    }
}

package org.kie.kogito.grafana.model.functions;

import java.util.List;

/**
 * Interface for a grafana/prometheus function. See <a href="https://prometheus.io/docs/prometheus/latest/querying/functions/">https://prometheus.io/docs/prometheus/latest/querying/functions/</>
 */
public interface GrafanaFunction {

    /**
     * Renders the expression.
     *
     * @return The rendered expression.
     */
    String render(String metricBody, List<Label> labels);
}

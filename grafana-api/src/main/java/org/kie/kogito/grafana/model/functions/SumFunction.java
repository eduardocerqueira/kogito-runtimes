package org.kie.kogito.grafana.model.functions;

import java.util.List;

public class SumFunction implements GrafanaFunction {

    private static final String RENDER_TEMPLATE = "sum(%s)";
    private GrafanaFunction grafanaFunction;

    public SumFunction(GrafanaFunction grafanaFunction) {
        this.grafanaFunction = grafanaFunction;
    }

    @Override
    public String render(String metricBody, List<Label> labels) {
        return String.format(RENDER_TEMPLATE, grafanaFunction.render(metricBody, labels));
    }
}

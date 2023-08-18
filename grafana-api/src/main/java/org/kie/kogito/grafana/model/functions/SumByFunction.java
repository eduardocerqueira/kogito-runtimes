package org.kie.kogito.grafana.model.functions;

import java.util.List;

public class SumByFunction implements GrafanaFunction {

    private static final String RENDER_TEMPLATE = "sum by (%s) (%s)";
    private GrafanaFunction grafanaFunction;
    private String sumByLabel;

    public SumByFunction(GrafanaFunction grafanaFunction, String sumByLabel) {
        this.grafanaFunction = grafanaFunction;
        this.sumByLabel = sumByLabel;
    }

    @Override
    public String render(String metricBody, List<Label> labels) {
        return String.format(RENDER_TEMPLATE, sumByLabel, grafanaFunction.render(metricBody, labels));
    }
}

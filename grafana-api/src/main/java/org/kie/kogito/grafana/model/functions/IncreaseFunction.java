package org.kie.kogito.grafana.model.functions;

import java.util.List;

public class IncreaseFunction implements GrafanaFunction {

    private static final String RENDER_TEMPLATE = "increase(%s[%s])";

    private String timeParameter;

    private GrafanaFunction grafanaFunction;

    public IncreaseFunction(GrafanaFunction grafanaFunction, String timeParameter) {
        if (timeParameter == null) {
            throw new IllegalArgumentException("timeParameter for Increase function can't be null");
        }
        this.grafanaFunction = grafanaFunction;
        this.timeParameter = timeParameter;
    }

    @Override
    public String render(String metricBody, List<Label> labels) {
        return String.format(RENDER_TEMPLATE, grafanaFunction.render(metricBody, labels), timeParameter);
    }
}

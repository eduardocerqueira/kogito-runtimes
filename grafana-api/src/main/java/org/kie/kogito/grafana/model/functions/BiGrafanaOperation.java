package org.kie.kogito.grafana.model.functions;

import java.util.List;

public class BiGrafanaOperation implements GrafanaFunction {

    private static final String RENDER_TEMPLATE = "(%s)%s(%s)";

    private GrafanaOperation operation;
    private GrafanaFunction firstOperand;
    private GrafanaFunction secondOperand;

    public BiGrafanaOperation(GrafanaOperation operation, GrafanaFunction firstOperand, GrafanaFunction secondOperand) {
        this.operation = operation;
        this.firstOperand = firstOperand;
        this.secondOperand = secondOperand;
    }

    @Override
    public String render(String metricBody, List<Label> labels) {
        return String.format(RENDER_TEMPLATE, firstOperand.render(metricBody, labels), operation.toString(), secondOperand.render(metricBody, labels));
    }
}

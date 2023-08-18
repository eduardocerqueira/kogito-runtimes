package org.kie.kogito.grafana.model.functions;

import java.util.List;
import java.util.stream.Collectors;

public class BaseExpression implements GrafanaFunction {

    private static final String RENDER_TEMPLATE = "%s_%s_%s{%s}";
    private String prefix;
    private String suffix;

    public BaseExpression(String prefix, String suffix) {
        this.prefix = prefix;
        this.suffix = suffix;
    }

    @Override
    public String render(String metricBody, List<Label> labels) {
        return String.format(RENDER_TEMPLATE, prefix, metricBody, suffix, labels.stream().map(Label::render).collect(Collectors.joining(",")));
    }
}

package org.kie.kogito.grafana.dmn;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.kie.kogito.grafana.model.functions.BaseExpression;
import org.kie.kogito.grafana.model.functions.BiGrafanaOperation;
import org.kie.kogito.grafana.model.functions.GrafanaFunction;
import org.kie.kogito.grafana.model.functions.GrafanaOperation;
import org.kie.kogito.grafana.model.functions.IncreaseFunction;
import org.kie.kogito.grafana.model.functions.SumFunction;
import org.kie.kogito.grafana.model.panel.common.YAxis;

public class LocalDateType extends AbstractDmnType {

    private static final String DMN_TYPE = "date";

    public LocalDateType() {
        super(LocalDate.class, DMN_TYPE);
        BaseExpression firstBaseExpression = new BaseExpression(DMN_TYPE, "sum");
        BaseExpression secondBaseExpression = new BaseExpression(DMN_TYPE, "count");

        GrafanaFunction firstOperand = new SumFunction(new IncreaseFunction(firstBaseExpression, "1m"));
        GrafanaFunction secondOperand = new SumFunction(new IncreaseFunction(secondBaseExpression, "1m"));

        setGrafanaFunction(new BiGrafanaOperation(GrafanaOperation.DIVISION, firstOperand, secondOperand));

        List<YAxis> yaxes = new ArrayList<>();
        yaxes.add(new YAxis("dateTimeAsIso", true));
        yaxes.add(new YAxis("ms", false));
        setYAxes(yaxes);
    }
}

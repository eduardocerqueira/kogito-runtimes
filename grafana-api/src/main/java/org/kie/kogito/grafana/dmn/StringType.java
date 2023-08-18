package org.kie.kogito.grafana.dmn;

import org.kie.kogito.grafana.model.functions.BaseExpression;
import org.kie.kogito.grafana.model.functions.IncreaseFunction;
import org.kie.kogito.grafana.model.functions.SumByFunction;

public class StringType extends AbstractDmnType {
    private static final String DMN_TYPE = "string";
    private static final String NAME_SUFFIX = "total";

    public StringType() {
        super(String.class, DMN_TYPE, NAME_SUFFIX);

        BaseExpression baseExpression = new BaseExpression(DMN_TYPE, NAME_SUFFIX);
        setGrafanaFunction(new SumByFunction(new IncreaseFunction(baseExpression, "1m"), "identifier"));
    }
}

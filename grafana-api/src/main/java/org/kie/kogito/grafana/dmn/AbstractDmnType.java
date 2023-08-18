package org.kie.kogito.grafana.dmn;

import java.util.List;

import org.kie.kogito.grafana.model.functions.GrafanaFunction;
import org.kie.kogito.grafana.model.panel.common.YAxis;

public class AbstractDmnType {

    private final Class internalRepresentationClass;

    private String dmnType;

    private GrafanaFunction grafanaFunction;

    private List<YAxis> yaxes;

    private String nameSuffix;

    public AbstractDmnType(Class internalRepresentationClass, String dmnType) {
        this(internalRepresentationClass, dmnType, "");
    }

    public AbstractDmnType(Class internalRepresentationClass, String dmnType, String nameSuffix) {
        this.internalRepresentationClass = internalRepresentationClass;
        this.dmnType = dmnType;
        this.nameSuffix = nameSuffix;
    }

    protected void setGrafanaFunction(GrafanaFunction grafanaFunction) {
        this.grafanaFunction = grafanaFunction;
    }

    protected void setYAxes(List<YAxis> yaxes) {
        this.yaxes = yaxes;
    }

    public String getNameSuffix() {
        return nameSuffix;
    }

    public Class getInternalClass() {
        return internalRepresentationClass;
    }

    public GrafanaFunction getGrafanaFunction() {
        return grafanaFunction;
    }

    public List<YAxis> getYaxes() {
        return yaxes;
    }

    public String getDmnType() {
        return dmnType;
    }
}

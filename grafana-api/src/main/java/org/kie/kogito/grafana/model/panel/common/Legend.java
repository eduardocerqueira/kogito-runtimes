package org.kie.kogito.grafana.model.panel.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Legend {

    @JsonProperty("alignAsTable")
    public boolean alignAsTable;

    @JsonProperty("avg")
    public boolean avg;

    @JsonProperty("current")
    public boolean current;

    @JsonProperty("hideEmpty")
    public boolean hideEmpty;

    @JsonProperty("hideZero")
    public boolean hideZero;

    @JsonProperty("legend")
    public boolean legend;

    @JsonProperty("max")
    public boolean max;

    @JsonProperty("min")
    public boolean min;

    @JsonProperty("rightSide")
    public boolean rightSide;

    @JsonProperty("show")
    public boolean show;

    @JsonProperty("total")
    public boolean total;

    @JsonProperty("values")
    public boolean values;
}

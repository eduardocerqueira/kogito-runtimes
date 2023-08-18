package org.kie.kogito.tracing.typedvalue;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UnitValue extends TypedValue {

    @JsonProperty("baseType")
    @JsonInclude(NON_NULL)
    protected String baseType;

    @JsonProperty("value")
    protected JsonNode value;

    protected UnitValue() {
    }

    public UnitValue(String type) {
        this(type, null, null);
    }

    public UnitValue(String type, JsonNode value) {
        this(type, null, value);
    }

    public UnitValue(String type, String baseType, JsonNode value) {
        super(Kind.UNIT, type);
        this.baseType = baseType;
        this.value = value;
    }

    public String getBaseType() {
        return baseType;
    }

    public JsonNode getValue() {
        return value;
    }
}

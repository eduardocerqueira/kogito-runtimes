package org.kie.kogito.tracing.typedvalue;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StructureValue extends TypedValue {

    @JsonProperty("value")
    protected Map<String, TypedValue> value;

    protected StructureValue() {
    }

    public StructureValue(String type) {
        super(Kind.STRUCTURE, type);
    }

    public StructureValue(String type, Map<String, TypedValue> value) {
        super(Kind.STRUCTURE, type);
        this.value = value;
    }

    public Map<String, TypedValue> getValue() {
        return value;
    }
}

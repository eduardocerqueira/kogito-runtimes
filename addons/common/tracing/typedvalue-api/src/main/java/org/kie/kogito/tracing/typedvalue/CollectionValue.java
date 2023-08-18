package org.kie.kogito.tracing.typedvalue;

import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CollectionValue extends TypedValue {

    @JsonProperty("value")
    protected Collection<TypedValue> value;

    protected CollectionValue() {
    }

    public CollectionValue(String type) {
        super(Kind.COLLECTION, type);
    }

    public CollectionValue(String type, Collection<TypedValue> value) {
        super(Kind.COLLECTION, type);
        this.value = value;
    }

    public Collection<TypedValue> getValue() {
        return value;
    }
}

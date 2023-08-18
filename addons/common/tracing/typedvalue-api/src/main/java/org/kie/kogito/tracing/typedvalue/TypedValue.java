package org.kie.kogito.tracing.typedvalue;

import com.fasterxml.jackson.annotation.JsonSubTypes;

@JsonSubTypes({
        @JsonSubTypes.Type(value = UnitValue.class, name = "UNIT"),
        @JsonSubTypes.Type(value = CollectionValue.class, name = "COLLECTION"),
        @JsonSubTypes.Type(value = StructureValue.class, name = "STRUCTURE")
})
public abstract class TypedValue extends BaseTypedValue<CollectionValue, StructureValue, UnitValue> {

    public TypedValue() {
    }

    public TypedValue(Kind kind, String type) {
        super(kind, type);
    }
}
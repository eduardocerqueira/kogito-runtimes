package org.kie.kogito.tracing.typedvalue;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXISTING_PROPERTY,
        defaultImpl = BaseTypedValue.Kind.class,
        property = "kind",
        visible = true)
public abstract class BaseTypedValue<C extends BaseTypedValue<C, S, U>, S extends BaseTypedValue<C, S, U>, U extends BaseTypedValue<C, S, U>> {

    public enum Kind {
        UNIT,
        COLLECTION,
        STRUCTURE
    }

    @JsonProperty("kind")
    protected Kind kind;

    @JsonProperty("type")
    protected String type;

    protected BaseTypedValue() {
    }

    protected BaseTypedValue(Kind kind, String type) {
        this.kind = kind;
        this.type = type;
    }

    public Kind getKind() {
        return kind;
    }

    public String getType() {
        return type;
    }

    @JsonIgnore
    public boolean isCollection() {
        return kind == Kind.COLLECTION;
    }

    @SuppressWarnings("unchecked")
    public C toCollection() {
        if (!isCollection()) {
            throw new IllegalStateException(String.format("Can't convert TypedValue of kind %s to COLLECTION", getKind()));
        }
        return (C) this;
    }

    @JsonIgnore
    public boolean isStructure() {
        return kind == Kind.STRUCTURE;
    }

    @SuppressWarnings("unchecked")
    public S toStructure() {
        if (!isStructure()) {
            throw new IllegalStateException(String.format("Can't convert TypedValue of kind %s to STRUCTURE", getKind()));
        }
        return (S) this;
    }

    @JsonIgnore
    public boolean isUnit() {
        return kind == Kind.UNIT;
    }

    @SuppressWarnings("unchecked")
    public U toUnit() {
        if (!isUnit()) {
            throw new IllegalStateException(String.format("Can't convert TypedValue of kind %s to UNIT", getKind()));
        }
        return (U) this;
    }
}
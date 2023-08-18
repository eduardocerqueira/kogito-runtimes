package org.jbpm.process.core.datatype.impl.type;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import org.jbpm.process.core.datatype.DataType;

/**
 * Representation of a boolean datatype.
 */
public final class BooleanDataType implements DataType {

    private static final long serialVersionUID = 510l;

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
    }

    @Override
    public boolean verifyDataType(final Object value) {
        return value == null || value instanceof Boolean;
    }

    @Override
    public Object readValue(String value) {
        return Boolean.parseBoolean(value);
    }

    @Override
    public String writeValue(Object value) {
        return (Boolean) value ? "true" : "false";
    }

    @Override
    public String getStringType() {
        return "java.lang.Boolean";
    }

    @Override
    public Class<?> getObjectClass() {
        return Boolean.class;
    }

}

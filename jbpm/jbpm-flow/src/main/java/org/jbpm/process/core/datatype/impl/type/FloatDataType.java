package org.jbpm.process.core.datatype.impl.type;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import org.jbpm.process.core.datatype.DataType;

/**
 * Representation of a float datatype.
 */
public final class FloatDataType implements DataType {

    private static final long serialVersionUID = 510l;

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
    }

    @Override
    public boolean verifyDataType(final Object value) {
        return value == null || value instanceof Float;
    }

    @Override
    public Object readValue(String value) {
        return Float.valueOf(value);
    }

    @Override
    public String writeValue(Object value) {
        Float f = (Float) value;
        return f == null ? "" : f.toString();
    }

    @Override
    public String getStringType() {
        return "java.lang.Float";
    }

    @Override
    public Class<?> getObjectClass() {
        return Float.class;
    }

}

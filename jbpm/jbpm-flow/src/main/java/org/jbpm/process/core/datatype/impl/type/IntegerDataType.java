package org.jbpm.process.core.datatype.impl.type;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import org.jbpm.process.core.datatype.DataType;

/**
 * Representation of an integer datatype.
 */
public class IntegerDataType implements DataType {

    private static final long serialVersionUID = 510l;

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
    }

    @Override
    public boolean verifyDataType(final Object value) {
        return value == null || value instanceof Integer;
    }

    @Override
    public Object readValue(String value) {
        return Integer.valueOf(value);
    }

    @Override
    public String writeValue(Object value) {
        Integer i = (Integer) value;
        return i == null ? "" : i.toString();
    }

    @Override
    public String getStringType() {
        return "java.lang.Integer";
    }

    @Override
    public Class<?> getObjectClass() {
        return Integer.class;
    }

}

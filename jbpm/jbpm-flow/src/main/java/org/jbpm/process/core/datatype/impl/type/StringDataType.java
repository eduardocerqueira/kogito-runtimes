package org.jbpm.process.core.datatype.impl.type;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import org.jbpm.process.core.datatype.DataType;

/**
 * Representation of a string datatype.
 */
public class StringDataType implements DataType {

    private static final long serialVersionUID = 510l;

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
    }

    @Override
    public boolean verifyDataType(final Object value) {
        return value == null || value instanceof String;
    }

    @Override
    public Object readValue(String value) {
        return value;
    }

    @Override
    public String writeValue(Object value) {
        return (String) value;
    }

    @Override
    public String getStringType() {
        return "java.lang.String";
    }

    @Override
    public Class<?> getObjectClass() {
        return String.class;
    }
}

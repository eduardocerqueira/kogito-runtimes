package org.jbpm.process.core.datatype.impl.type;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import org.jbpm.process.core.datatype.DataType;

/**
 * Representation of an undefined datatype.
 */
public final class UndefinedDataType implements DataType {

    private static final long serialVersionUID = 510l;
    private static UndefinedDataType instance;

    public static UndefinedDataType getInstance() {
        if (instance == null) {
            instance = new UndefinedDataType();
        }
        return instance;
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
    }

    @Override
    public boolean verifyDataType(final Object value) {
        return value == null;
    }

    @Override
    public Object readValue(String value) {
        throw new IllegalArgumentException("Undefined datatype");
    }

    @Override
    public String writeValue(Object value) {
        throw new IllegalArgumentException("Undefined datatype");
    }

    @Override
    public String getStringType() {
        return "java.lang.Object";
    }

}

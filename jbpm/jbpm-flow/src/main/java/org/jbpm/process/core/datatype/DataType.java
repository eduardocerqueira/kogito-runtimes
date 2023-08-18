package org.jbpm.process.core.datatype;

import java.io.Externalizable;

/**
 * Abstract representation of a datatype.
 */
public interface DataType extends Externalizable {

    /**
     * Returns true if the given value is a valid value of this data type.
     */
    boolean verifyDataType(Object value);

    String writeValue(Object value);

    Object readValue(String value);

    /**
     * Returns the corresponding Java type of this datatype
     */
    String getStringType();

    default Class<?> getObjectClass() {
        return Object.class;
    }

    default boolean isAssignableFrom(DataType dataType) {
        return DataTypeUtils.isAssignableFrom(this, dataType);
    }

    default Object clone(Object value) {
        return value;
    }
}

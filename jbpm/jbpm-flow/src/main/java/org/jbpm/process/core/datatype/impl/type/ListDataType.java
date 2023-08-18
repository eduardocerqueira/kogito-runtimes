package org.jbpm.process.core.datatype.impl.type;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.List;
import java.util.Objects;

import org.jbpm.process.core.TypeObject;
import org.jbpm.process.core.datatype.DataType;

/**
 * Representation of a list datatype.
 * All elements in the list must have the same datatype.
 */
public class ListDataType extends ObjectDataType implements TypeObject {

    private static final long serialVersionUID = 510l;

    private DataType dataType;

    public ListDataType() {
        setClassName("java.util.List");
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        dataType = (DataType) in.readObject();
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(dataType);
    }

    public ListDataType(DataType dataType) {
        setType(dataType);
    }

    public void setType(final DataType dataType) {
        this.dataType = dataType;
    }

    public DataType getType() {
        return this.dataType;
    }

    @Override
    public boolean verifyDataType(final Object value) {
        if (value == null) {
            return true;
        }
        if (value instanceof List) {
            for (Object o : (List<?>) value) {
                if (dataType != null && !dataType.verifyDataType(o)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        ListDataType that = (ListDataType) o;
        return Objects.equals(dataType, that.dataType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), dataType);
    }
}

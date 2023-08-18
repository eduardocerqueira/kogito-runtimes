package org.jbpm.process.core.datatype;

import java.io.Serializable;

/**
 * A factory for creating a datatype.
 */
public interface DataTypeFactory extends Serializable {

    DataType createDataType();

}

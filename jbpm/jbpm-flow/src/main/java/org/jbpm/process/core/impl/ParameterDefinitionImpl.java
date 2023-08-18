package org.jbpm.process.core.impl;

import java.io.Serializable;

import org.jbpm.process.core.ParameterDefinition;
import org.jbpm.process.core.datatype.DataType;

public class ParameterDefinitionImpl implements ParameterDefinition, Serializable {

    private static final long serialVersionUID = 510l;

    private String name;
    private DataType type;

    public ParameterDefinitionImpl() {
    }

    public ParameterDefinitionImpl(String name, DataType type) {
        setName(name);
        setType(type);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Name cannot be null");
        }
        this.name = name;
    }

    public DataType getType() {
        return type;
    }

    public void setType(DataType type) {
        if (type == null) {
            throw new IllegalArgumentException("Data type cannot be null");
        }
        this.type = type;
    }

    public String toString() {
        return name;
    }
}

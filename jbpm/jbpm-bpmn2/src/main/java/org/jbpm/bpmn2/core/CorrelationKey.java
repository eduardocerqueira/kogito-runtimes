package org.jbpm.bpmn2.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CorrelationKey implements Serializable {

    private static final long serialVersionUID = -8480594946115255764L;

    private String id;
    private String name;

    private List<String> propertiesRef;

    public CorrelationKey() {
        propertiesRef = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<String> getPropertiesRef() {
        return propertiesRef;
    }

}
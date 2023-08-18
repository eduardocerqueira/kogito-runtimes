package org.jbpm.bpmn2.core;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class CorrelationSubscription implements Serializable {

    private static final long serialVersionUID = 1206313928584632165L;

    private String id;
    private String name;
    private String correlationKeyRef;
    private Map<String, Expression> propertyExpressions;

    public CorrelationSubscription() {
        this.propertyExpressions = new HashMap<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCorrelationKeyRef() {
        return correlationKeyRef;
    }

    public void setCorrelationKeyRef(String correlationKeyRef) {
        this.correlationKeyRef = correlationKeyRef;
    }

    public Map<String, Expression> getPropertyExpressions() {
        return propertyExpressions;
    }
}
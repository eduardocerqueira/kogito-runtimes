package org.jbpm.bpmn2.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Collaboration implements Serializable {

    private static final long serialVersionUID = 8676065262126603722L;
    private String id;
    private String name;
    private List<String> processesRef;

    private List<CorrelationKey> correlationKeys;

    public Collaboration() {
        correlationKeys = new ArrayList<>();
        processesRef = new ArrayList<>();
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

    public void addCorrelationKey(CorrelationKey key) {
        this.correlationKeys.add(key);
    }

    public List<CorrelationKey> getCorrelationKeys() {
        return this.correlationKeys;
    }

    public List<String> getProcessesRef() {
        return processesRef;
    }

    public void setProcessesRef(List<String> processesRef) {
        this.processesRef = processesRef;
    }
}
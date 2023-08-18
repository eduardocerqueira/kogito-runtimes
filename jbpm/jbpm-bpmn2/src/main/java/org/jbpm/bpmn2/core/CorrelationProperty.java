package org.jbpm.bpmn2.core;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CorrelationProperty implements Serializable {

    private static final long serialVersionUID = 2372340998854641672L;
    private String id;
    private String name;
    private String type;

    private Map<String, Expression> retrievalExpression;

    public CorrelationProperty() {
        retrievalExpression = new HashMap<>();
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

    public String getType() {
        return type;
    }

    public List<String> getMessageRefs() {
        return retrievalExpression.keySet().stream().collect(Collectors.toList());
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setRetrievalExpression(String messageRef, Expression retrievalExpression) {
        this.retrievalExpression.put(messageRef, retrievalExpression);
    }

    public Expression getRetrievalExpression(String messageRef) {
        return retrievalExpression.get(messageRef);
    }
}
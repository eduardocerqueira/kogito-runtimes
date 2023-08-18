package org.jbpm.process.core.correlation;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CorrelationProperties implements Serializable {

    private static final long serialVersionUID = 4740957354662392843L;

    private Map<String, CorrelationExpressionEvaluator> properties;

    public CorrelationProperties() {
        this.properties = new HashMap<>();
    }

    public void addProperty(String name, CorrelationExpressionEvaluator expression) {
        properties.put(name, expression);
    }

    public void removeProperty(String name) {
        properties.remove(name);
    }

    public Collection<String> names() {
        return properties.keySet();
    }

    public CorrelationExpressionEvaluator getExpressionFor(String propertyName) {
        return properties.get(propertyName);
    }

}
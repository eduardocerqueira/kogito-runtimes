package org.jbpm.process.core.correlation;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Correlation implements Serializable {

    private static final long serialVersionUID = 4214786559199084264L;

    private String id;
    private String name;

    private Map<String, CorrelationProperties> correlationPropertiesByMessage;
    private CorrelationProperties processCorrelationProperties;

    public Correlation(String id, String name) {
        this.id = id;
        this.name = name;
        this.correlationPropertiesByMessage = new HashMap<>();
    }

    public CorrelationProperties getMessageCorrelationFor(String messageRef) {
        return correlationPropertiesByMessage.computeIfAbsent(messageRef, ref -> new CorrelationProperties());
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void subscribe() {
        if (isSubscribed()) {
            return;
        }
        processCorrelationProperties = new CorrelationProperties();
    }

    public boolean isSubscribed() {
        return processCorrelationProperties != null;
    }

    public CorrelationProperties getProcessSubscription() {
        return processCorrelationProperties;
    }

    public boolean hasCorrelationFor(String messageRef) {
        return correlationPropertiesByMessage.containsKey(messageRef);
    }
}
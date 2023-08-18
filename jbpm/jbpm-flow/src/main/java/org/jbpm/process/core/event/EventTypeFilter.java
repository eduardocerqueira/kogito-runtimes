package org.jbpm.process.core.event;

import java.io.Serializable;
import java.util.function.Function;

import org.jbpm.process.core.correlation.CorrelationInstance;
import org.jbpm.process.core.correlation.CorrelationManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EventTypeFilter implements EventFilter, Serializable {

    private static final Logger logger = LoggerFactory.getLogger(EventTypeFilter.class);

    private static final long serialVersionUID = 510l;

    protected String type;

    private String messageRef;

    private CorrelationManager correlationManager;

    @Override
    public boolean isCorrelated() {
        return messageRef != null;
    }

    public void setCorrelationManager(CorrelationManager correlationManager) {
        this.correlationManager = correlationManager;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String toString() {
        return "Event filter: [" + this.type + "]";
    }

    @Override
    public boolean acceptsEvent(String type, Object event, Function<String, Object> resolver) {
        if (resolver == null) {
            return this.type != null && this.type.equals(type);
        }

        if (this.type != null && this.type.equals(type)) {
            if (correlationManager != null && correlationManager.isSubscribe(messageRef)) {
                if (event == null) {
                    logger.debug("This event is subscribed to a message ref {}", type);
                    return false;
                }
                CorrelationInstance messageCorrelation = correlationManager.computeCorrelationInstance(messageRef, event);
                CorrelationInstance processCorrelation = correlationManager.computeSubscription(messageRef, resolver);
                logger.debug("The event type {} is correlated, computing correlations. Message correlation is {}; process correlation is: {} ", type, messageCorrelation, processCorrelation);
                return messageCorrelation.equals(processCorrelation);
            }
            return true;
        }

        String resolvedType = (String) resolver.apply(this.type);
        return resolvedType != null && resolvedType.equals(type);

    }

    public void setMessageRef(String messageRef) {
        this.messageRef = messageRef;
    }
}

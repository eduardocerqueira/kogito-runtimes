package org.kie.kogito.internal.process.event;

import java.util.Collections;
import java.util.Set;

import org.kie.kogito.process.EventDescription;

public interface KogitoEventListener {

    /**
     * Returns unique set of event descriptions that this event listener is interested in.
     *
     * @return returns set of event definitions awaiting or empty set
     */
    default Set<EventDescription<?>> getEventDescriptions() {
        return Collections.emptySet();
    }

    /**
     * Signals that an event has occurred. The type parameter defines
     * which type of event and the event parameter can contain additional information
     * related to the event.
     *
     * @param type the type of event
     * @param event the data associated with this event
     */
    void signalEvent(String type, Object event);

    /**
     * Returns the event types this event listener is interested in.
     * May return <code>null</code> if the event types are unknown.
     */
    String[] getEventTypes();
}

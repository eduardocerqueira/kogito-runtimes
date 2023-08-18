package org.kie.kogito.tracing;

public interface EventEmitter {

    /**
     * Emit payload as an event.
     * 
     * @param payload Payload to emit.
     */
    void emit(final String payload);
}

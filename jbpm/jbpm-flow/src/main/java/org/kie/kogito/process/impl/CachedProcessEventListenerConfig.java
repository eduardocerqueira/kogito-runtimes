package org.kie.kogito.process.impl;

import java.util.ArrayList;
import java.util.List;

import org.kie.api.event.process.ProcessEventListener;
import org.kie.kogito.process.ProcessEventListenerConfig;

public class CachedProcessEventListenerConfig implements ProcessEventListenerConfig {

    private final List<ProcessEventListener> processEventListeners;

    public CachedProcessEventListenerConfig() {
        this.processEventListeners = new ArrayList<>();
    }

    public CachedProcessEventListenerConfig(List<ProcessEventListener> processEventListeners) {
        this.processEventListeners = processEventListeners;
    }

    public CachedProcessEventListenerConfig register(ProcessEventListener listener) {
        processEventListeners.add(listener);
        return this;
    }

    @Override
    public List<ProcessEventListener> listeners() {
        return processEventListeners;
    }

}

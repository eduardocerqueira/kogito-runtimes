package org.kie.kogito.process.impl;

import org.kie.api.event.process.ProcessEventListener;

public class DefaultProcessEventListenerConfig extends CachedProcessEventListenerConfig {

    public DefaultProcessEventListenerConfig(ProcessEventListener... listeners) {
        for (ProcessEventListener listener : listeners) {
            register(listener);
        }
    }
}

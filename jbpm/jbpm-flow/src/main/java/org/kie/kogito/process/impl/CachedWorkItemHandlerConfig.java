package org.kie.kogito.process.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import org.kie.kogito.internal.process.runtime.KogitoWorkItemHandler;
import org.kie.kogito.process.WorkItemHandlerConfig;

public class CachedWorkItemHandlerConfig implements WorkItemHandlerConfig {

    private final Map<String, KogitoWorkItemHandler> workItemHandlers = new HashMap<>();

    public CachedWorkItemHandlerConfig register(String name, KogitoWorkItemHandler handler) {
        workItemHandlers.put(name, handler);
        return this;
    }

    @Override
    public KogitoWorkItemHandler forName(String name) {
        KogitoWorkItemHandler workItemHandler = workItemHandlers.get(name);
        if (workItemHandler == null) {
            throw new NoSuchElementException(name);
        } else {
            return workItemHandler;
        }
    }

    @Override
    public Collection<String> names() {
        return workItemHandlers.keySet();
    }
}

package org.kie.kogito.process.impl;

import java.util.Collection;
import java.util.HashSet;
import java.util.NoSuchElementException;

import org.kie.kogito.internal.process.runtime.KogitoWorkItemHandler;
import org.kie.kogito.process.WorkItemHandlerConfig;

public class MultiWorkItemHandlerConfig implements WorkItemHandlerConfig {

    private final Iterable<WorkItemHandlerConfig> workItemHandlerConfigs;

    public MultiWorkItemHandlerConfig(Iterable<WorkItemHandlerConfig> workItemHandlerConfigs) {
        this.workItemHandlerConfigs = workItemHandlerConfigs;
    }

    @Override
    public KogitoWorkItemHandler forName(String name) {
        RuntimeException trackException = null;
        for (WorkItemHandlerConfig workItemHandlerConfig : workItemHandlerConfigs) {
            try {
                return workItemHandlerConfig.forName(name);
            } catch (RuntimeException ex) {
                trackException = ex;
            }
        }
        throw trackException != null ? trackException : new NoSuchElementException("Cannot find work item for name " + name);
    }

    @Override
    public Collection<String> names() {
        Collection<String> names = new HashSet<>();
        workItemHandlerConfigs.forEach(w -> names.addAll(w.names()));
        return names;
    }
}

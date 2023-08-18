package org.kie.kogito.process;

import java.util.Collection;

import org.kie.kogito.internal.process.runtime.KogitoWorkItemHandler;

public interface WorkItemHandlerConfig {

    KogitoWorkItemHandler forName(String name);

    Collection<String> names();
}

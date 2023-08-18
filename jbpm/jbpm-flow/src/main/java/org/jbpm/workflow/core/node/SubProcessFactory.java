package org.jbpm.workflow.core.node;

import org.kie.api.runtime.process.ProcessContext;
import org.kie.kogito.process.ProcessInstance;

public interface SubProcessFactory<T> {
    T bind(ProcessContext ctx);

    ProcessInstance<T> createInstance(T model);

    void unbind(ProcessContext ctx, T model);
}

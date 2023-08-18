package org.kie.kogito.process;

public interface ProcessInstancesFactory {

    MutableProcessInstances<?> createProcessInstances(Process<?> process);
}

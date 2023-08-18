package org.jbpm.process.instance;

import org.drools.core.common.InternalWorkingMemory;
import org.drools.core.runtime.process.ProcessRuntimeFactoryService;
import org.kie.kogito.Application;

public class ProcessRuntimeFactoryServiceImpl implements ProcessRuntimeFactoryService {

    private Application application;

    public ProcessRuntimeFactoryServiceImpl() {
    }

    public ProcessRuntimeFactoryServiceImpl(Application application) {
        this.application = application;
    }

    @Override
    public InternalProcessRuntime newProcessRuntime(InternalWorkingMemory workingMemory) {
        return new ProcessRuntimeImpl(application, workingMemory);
    }

}

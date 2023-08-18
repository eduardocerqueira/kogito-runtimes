package org.jbpm.process.instance;

import org.drools.core.common.InternalKnowledgeRuntime;
import org.drools.core.common.InternalWorkingMemory;
import org.kie.api.runtime.process.ProcessRuntime;
import org.kie.kogito.Application;
import org.kie.kogito.internal.process.event.KogitoProcessEventSupport;
import org.kie.kogito.internal.process.runtime.KogitoProcessRuntime;
import org.kie.kogito.jobs.JobsService;
import org.kie.kogito.signal.SignalManager;
import org.kie.kogito.uow.UnitOfWorkManager;

public interface InternalProcessRuntime extends org.drools.core.runtime.process.InternalProcessRuntime, KogitoProcessRuntime.Provider {

    ProcessInstanceManager getProcessInstanceManager();

    SignalManager getSignalManager();

    KogitoProcessEventSupport getProcessEventSupport();

    UnitOfWorkManager getUnitOfWorkManager();

    InternalKnowledgeRuntime getInternalKieRuntime();

    JobsService getJobsService();

    Application getApplication();

    static KogitoProcessRuntime asKogitoProcessRuntime(ProcessRuntime kogitoProcessRuntimeProvider) {
        if (kogitoProcessRuntimeProvider instanceof KogitoProcessRuntime) {
            return (KogitoProcessRuntime) kogitoProcessRuntimeProvider;
        }
        if (kogitoProcessRuntimeProvider instanceof KogitoProcessRuntime.Provider) {
            return ((KogitoProcessRuntime.Provider) kogitoProcessRuntimeProvider).getKogitoProcessRuntime();
        }
        // this line is used only for legacy tests
        return ((KogitoProcessRuntime.Provider) ((InternalWorkingMemory) kogitoProcessRuntimeProvider).getProcessRuntime()).getKogitoProcessRuntime();
    }
}

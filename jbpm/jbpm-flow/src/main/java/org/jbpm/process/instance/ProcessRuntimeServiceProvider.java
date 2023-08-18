package org.jbpm.process.instance;

import org.kie.kogito.internal.process.event.KogitoProcessEventSupport;
import org.kie.kogito.internal.process.runtime.KogitoWorkItemManager;
import org.kie.kogito.jobs.JobsService;
import org.kie.kogito.signal.SignalManager;
import org.kie.kogito.uow.UnitOfWorkManager;

public interface ProcessRuntimeServiceProvider {

    JobsService getJobsService();

    ProcessInstanceManager getProcessInstanceManager();

    SignalManager getSignalManager();

    KogitoWorkItemManager getKogitoWorkItemManager();

    KogitoProcessEventSupport getEventSupport();

    UnitOfWorkManager getUnitOfWorkManager();
}

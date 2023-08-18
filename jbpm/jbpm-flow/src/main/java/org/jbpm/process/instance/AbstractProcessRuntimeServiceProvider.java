package org.jbpm.process.instance;

import java.util.Optional;

import org.jbpm.process.instance.event.KogitoProcessEventSupportImpl;
import org.jbpm.process.instance.impl.DefaultProcessInstanceManager;
import org.kie.api.event.process.ProcessEventListener;
import org.kie.kogito.auth.IdentityProvider;
import org.kie.kogito.internal.process.event.KogitoProcessEventListener;
import org.kie.kogito.internal.process.event.KogitoProcessEventSupport;
import org.kie.kogito.internal.process.runtime.KogitoWorkItemManager;
import org.kie.kogito.jobs.JobsService;
import org.kie.kogito.process.ProcessEventListenerConfig;
import org.kie.kogito.process.WorkItemHandlerConfig;
import org.kie.kogito.services.signal.LightSignalManager;
import org.kie.kogito.signal.SignalManager;
import org.kie.kogito.signal.SignalManagerHub;
import org.kie.kogito.uow.UnitOfWorkManager;
import org.kie.kogito.uow.events.UnitOfWorkProcessEventListener;

public class AbstractProcessRuntimeServiceProvider implements ProcessRuntimeServiceProvider {

    private final JobsService jobsService;
    private final ProcessInstanceManager processInstanceManager;
    private final SignalManager signalManager;
    private final KogitoWorkItemManager workItemManager;
    private final KogitoProcessEventSupport eventSupport;
    private final UnitOfWorkManager unitOfWorkManager;

    public AbstractProcessRuntimeServiceProvider(JobsService jobsService,
            WorkItemHandlerConfig workItemHandlerProvider,
            ProcessEventListenerConfig processEventListenerProvider,
            SignalManagerHub compositeSignalManager,
            UnitOfWorkManager unitOfWorkManager,
            IdentityProvider identityProvider) {
        this.unitOfWorkManager = unitOfWorkManager;
        processInstanceManager = new DefaultProcessInstanceManager();
        signalManager = new LightSignalManager(
                id -> Optional.ofNullable(
                        processInstanceManager.getProcessInstance(id)),
                compositeSignalManager);
        this.eventSupport = new KogitoProcessEventSupportImpl(identityProvider);
        this.jobsService = jobsService;
        this.workItemManager = new LightWorkItemManager(processInstanceManager, signalManager, eventSupport);

        for (String workItem : workItemHandlerProvider.names()) {
            workItemManager.registerWorkItemHandler(
                    workItem, workItemHandlerProvider.forName(workItem));
        }

        this.eventSupport.addEventListener(new UnitOfWorkProcessEventListener(unitOfWorkManager));
        for (ProcessEventListener listener : processEventListenerProvider.listeners()) {
            this.eventSupport.addEventListener((KogitoProcessEventListener) listener);
        }
    }

    @Override
    public JobsService getJobsService() {
        return jobsService;
    }

    @Override
    public ProcessInstanceManager getProcessInstanceManager() {
        return processInstanceManager;
    }

    @Override
    public SignalManager getSignalManager() {
        return signalManager;
    }

    @Override
    public KogitoWorkItemManager getKogitoWorkItemManager() {
        return workItemManager;
    }

    @Override
    public KogitoProcessEventSupport getEventSupport() {
        return eventSupport;
    }

    @Override
    public UnitOfWorkManager getUnitOfWorkManager() {
        return unitOfWorkManager;
    }
}

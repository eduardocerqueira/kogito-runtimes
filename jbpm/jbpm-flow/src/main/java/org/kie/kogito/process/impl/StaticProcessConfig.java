package org.kie.kogito.process.impl;

import org.kie.kogito.auth.IdentityProvider;
import org.kie.kogito.jobs.JobsService;
import org.kie.kogito.process.ProcessConfig;
import org.kie.kogito.process.ProcessEventListenerConfig;
import org.kie.kogito.process.ProcessVersionResolver;
import org.kie.kogito.process.WorkItemHandlerConfig;
import org.kie.kogito.services.identity.NoOpIdentityProvider;
import org.kie.kogito.services.signal.DefaultSignalManagerHub;
import org.kie.kogito.services.uow.CollectingUnitOfWorkFactory;
import org.kie.kogito.services.uow.DefaultUnitOfWorkManager;
import org.kie.kogito.signal.SignalManagerHub;
import org.kie.kogito.uow.UnitOfWorkManager;

public class StaticProcessConfig implements ProcessConfig {

    private final WorkItemHandlerConfig workItemHandlerConfig;
    private final ProcessEventListenerConfig processEventListenerConfig;
    private final SignalManagerHub signalManager;
    private final UnitOfWorkManager unitOfWorkManager;
    private final JobsService jobsService;
    private final ProcessVersionResolver versionResolver;

    private final IdentityProvider identityProvider;

    public StaticProcessConfig(
            WorkItemHandlerConfig workItemHandlerConfig,
            ProcessEventListenerConfig processEventListenerConfig,
            UnitOfWorkManager unitOfWorkManager) {
        this(workItemHandlerConfig, processEventListenerConfig, unitOfWorkManager, null, null, new NoOpIdentityProvider());
    }

    public StaticProcessConfig(
            WorkItemHandlerConfig workItemHandlerConfig,
            ProcessEventListenerConfig processEventListenerConfig,
            UnitOfWorkManager unitOfWorkManager,
            JobsService jobsService,
            ProcessVersionResolver versionResolver,
            IdentityProvider identityProvider) {
        this.unitOfWorkManager = unitOfWorkManager;
        this.workItemHandlerConfig = workItemHandlerConfig;
        this.processEventListenerConfig = processEventListenerConfig;
        this.signalManager = new DefaultSignalManagerHub();
        this.jobsService = jobsService;
        this.versionResolver = versionResolver;
        this.identityProvider = identityProvider;
    }

    public StaticProcessConfig() {
        this(new DefaultWorkItemHandlerConfig(),
                new DefaultProcessEventListenerConfig(),
                new DefaultUnitOfWorkManager(new CollectingUnitOfWorkFactory()),
                null,
                null,
                new NoOpIdentityProvider());
    }

    @Override
    public WorkItemHandlerConfig workItemHandlers() {
        return this.workItemHandlerConfig;
    }

    @Override
    public ProcessEventListenerConfig processEventListeners() {
        return this.processEventListenerConfig;
    }

    @Override
    public SignalManagerHub signalManagerHub() {
        return this.signalManager;
    }

    @Override
    public UnitOfWorkManager unitOfWorkManager() {
        return this.unitOfWorkManager;
    }

    @Override
    public JobsService jobsService() {
        return jobsService;
    }

    @Override
    public ProcessVersionResolver versionResolver() {
        return versionResolver;
    }

    @Override
    public IdentityProvider identityProvider() {
        return identityProvider;
    }
}

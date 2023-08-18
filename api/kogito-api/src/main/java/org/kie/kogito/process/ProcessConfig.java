package org.kie.kogito.process;

import org.kie.kogito.KogitoConfig;
import org.kie.kogito.auth.IdentityProvider;
import org.kie.kogito.jobs.JobsService;
import org.kie.kogito.signal.SignalManagerHub;
import org.kie.kogito.uow.UnitOfWorkManager;

public interface ProcessConfig extends KogitoConfig {
    WorkItemHandlerConfig workItemHandlers();

    ProcessEventListenerConfig processEventListeners();

    SignalManagerHub signalManagerHub();

    UnitOfWorkManager unitOfWorkManager();

    JobsService jobsService();

    ProcessVersionResolver versionResolver();

    IdentityProvider identityProvider();
}

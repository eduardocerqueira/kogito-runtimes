package $Package$;

import javax.enterprise.inject.Instance;

import org.kie.api.event.process.ProcessEventListener;
import org.kie.kogito.auth.IdentityProvider;
import org.kie.kogito.event.EventPublisher;
import org.kie.kogito.jobs.JobsService;
import org.kie.kogito.process.ProcessEventListenerConfig;
import org.kie.kogito.process.ProcessVersionResolver;
import org.kie.kogito.process.WorkItemHandlerConfig;
import org.kie.kogito.uow.UnitOfWorkManager;
import org.kie.kogito.uow.events.UnitOfWorkEventListener;

@javax.inject.Singleton
public class ProcessConfig extends org.kie.kogito.process.impl.AbstractProcessConfig {

    @javax.inject.Inject
    public ProcessConfig(
            Instance<WorkItemHandlerConfig> workItemHandlerConfig,
            Instance<UnitOfWorkManager> unitOfWorkManager,
            Instance<JobsService> jobsService,
            Instance<ProcessEventListenerConfig> processEventListenerConfigs,
            Instance<ProcessEventListener> processEventListeners,
            Instance<EventPublisher> eventPublishers,
            org.kie.kogito.config.ConfigBean configBean,
            Instance<UnitOfWorkEventListener> unitOfWorkEventListeners,
            Instance<ProcessVersionResolver> versionResolver,
            Instance<IdentityProvider> identityProvider) {

        super(workItemHandlerConfig,
                processEventListenerConfigs,
                processEventListeners,
                unitOfWorkManager,
                jobsService,
                eventPublishers,
                configBean.getServiceUrl(),
                unitOfWorkEventListeners,
                versionResolver,
                identityProvider);
    }

}

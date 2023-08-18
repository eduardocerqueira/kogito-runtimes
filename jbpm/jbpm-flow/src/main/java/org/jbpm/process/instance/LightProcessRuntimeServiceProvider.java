package org.jbpm.process.instance;

import org.kie.kogito.process.impl.DefaultProcessEventListenerConfig;
import org.kie.kogito.process.impl.DefaultWorkItemHandlerConfig;
import org.kie.kogito.services.identity.NoOpIdentityProvider;
import org.kie.kogito.services.signal.DefaultSignalManagerHub;
import org.kie.kogito.services.uow.CollectingUnitOfWorkFactory;
import org.kie.kogito.services.uow.DefaultUnitOfWorkManager;

public class LightProcessRuntimeServiceProvider extends AbstractProcessRuntimeServiceProvider {

    public LightProcessRuntimeServiceProvider() {
        super(null,
                new DefaultWorkItemHandlerConfig(),
                new DefaultProcessEventListenerConfig(),
                new DefaultSignalManagerHub(),
                new DefaultUnitOfWorkManager(new CollectingUnitOfWorkFactory()),
                new NoOpIdentityProvider());
    }
}

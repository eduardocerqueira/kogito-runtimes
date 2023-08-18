package org.kie.kogito.process.impl;

import org.jbpm.process.instance.AbstractProcessRuntimeServiceProvider;
import org.kie.kogito.process.ProcessConfig;

public class ConfiguredProcessServices extends AbstractProcessRuntimeServiceProvider {

    public ConfiguredProcessServices(ProcessConfig config) {
        super(config.jobsService(),
                config.workItemHandlers(),
                config.processEventListeners(),
                config.signalManagerHub(),
                config.unitOfWorkManager(),
                config.identityProvider());

    }
}

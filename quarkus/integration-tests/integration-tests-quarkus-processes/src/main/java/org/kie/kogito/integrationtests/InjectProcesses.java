package org.kie.kogito.integrationtests;

import javax.inject.Inject;

import org.kie.kogito.Application;
import org.kie.kogito.process.ProcessConfig;
import org.kie.kogito.process.Processes;

import io.quarkus.runtime.Startup;

@Startup
public class InjectProcesses {

    @Inject
    public InjectProcesses(Processes processes, Application application) {
        if (processes != application.get(Processes.class)) {
            throw new IllegalStateException("Processes should be injectable and same as instance application.get(Processes.class)");
        }
        if (application.config().get(ProcessConfig.class) == null) {
            throw new IllegalStateException("ProcessConfig not available");
        }
    }
}

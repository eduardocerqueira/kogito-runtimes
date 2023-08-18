package org.kie.kogito.integrationtests;

import org.springframework.beans.factory.annotation.Autowired;
import org.kie.kogito.Application;
import org.kie.kogito.process.Processes;
import org.kie.kogito.process.ProcessConfig;

public class SpringInjectProcesses {

    @Autowired
    public SpringInjectProcesses(Processes processes, Application application) {
        if(processes != application.get(Processes.class)) {
            throw new IllegalStateException("Processes should be injectable and same as instance application.get(Processes.class)");
        }
        if(application.config().get(ProcessConfig.class) == null) {
            throw new IllegalStateException("ProcessConfig not available");
        }
    }
}

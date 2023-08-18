package org.kie.kogito.core.process.incubation.quarkus.support;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

import org.kie.kogito.incubation.common.DataContext;
import org.kie.kogito.incubation.common.Id;
import org.kie.kogito.incubation.processes.services.StraightThroughProcessService;
import org.kie.kogito.process.Processes;

@ApplicationScoped
public class QuarkusStraightThroughProcessService implements StraightThroughProcessService {
    @Inject
    Instance<Processes> processesInstance;
    StraightThroughProcessServiceImpl delegate;

    @PostConstruct
    void startup() {
        this.delegate = new StraightThroughProcessServiceImpl(processesInstance.get());
    }

    @Override
    public DataContext evaluate(Id processId, DataContext inputContext) {
        return delegate.evaluate(processId, inputContext);
    }

}

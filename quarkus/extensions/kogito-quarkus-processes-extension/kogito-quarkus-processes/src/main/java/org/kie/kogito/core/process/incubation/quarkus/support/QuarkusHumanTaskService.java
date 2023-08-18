package org.kie.kogito.core.process.incubation.quarkus.support;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

import org.kie.kogito.Application;
import org.kie.kogito.incubation.common.DataContext;
import org.kie.kogito.incubation.common.ExtendedDataContext;
import org.kie.kogito.incubation.common.LocalId;
import org.kie.kogito.incubation.common.MetaDataContext;
import org.kie.kogito.incubation.processes.services.humantask.HumanTaskService;
import org.kie.kogito.process.Processes;
import org.kie.kogito.process.impl.ProcessServiceImpl;

@ApplicationScoped
public class QuarkusHumanTaskService implements HumanTaskService {
    @Inject
    Instance<Processes> processesInstance;
    @Inject
    Application application;
    HumanTaskServiceImpl delegate;

    @PostConstruct
    void startup() {
        this.delegate = new HumanTaskServiceImpl(application, new ProcessServiceImpl(application), processesInstance.get());
    }

    @Override
    public ExtendedDataContext get(LocalId id, MetaDataContext meta) {
        return delegate.get(id, meta);
    }

    @Override
    public ExtendedDataContext create(LocalId taskId) {
        return delegate.create(taskId);
    }

    @Override
    public ExtendedDataContext abort(LocalId taskId, MetaDataContext meta) {
        return delegate.abort(taskId, meta);
    }

    @Override
    public ExtendedDataContext complete(LocalId processId, DataContext dataContext) {
        return delegate.complete(processId, dataContext);
    }

    @Override
    public ExtendedDataContext update(LocalId processId, DataContext dataContext) {
        return null;
    }

    @Override
    public ExtendedDataContext transition(LocalId taskId, DataContext dataContext) {
        return delegate.transition(taskId, dataContext);
    }
}

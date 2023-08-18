package org.kie.kogito.addon.cloudevents.spring;

import java.util.Collections;
import java.util.concurrent.ExecutorService;

import org.kie.kogito.Application;
import org.kie.kogito.Model;
import org.kie.kogito.event.EventExecutorServiceFactory;
import org.kie.kogito.event.EventReceiver;
import org.kie.kogito.event.impl.AbstractMessageConsumer;
import org.kie.kogito.process.Process;
import org.kie.kogito.process.ProcessService;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class SpringMessageConsumer<M extends Model, D> extends AbstractMessageConsumer<M, D> {

    @Autowired
    Application application;

    @Autowired
    ProcessService processService;

    @Autowired
    EventExecutorServiceFactory factory;

    private ExecutorService executor;

    protected void init(Process<M> process, String trigger, Class<D> objectClass, EventReceiver eventReceiver) {
        executor = factory.getExecutorService(trigger);
        init(application, process, trigger, eventReceiver, objectClass, processService, executor, Collections.emptySet());
    }

    public void close() {
        executor.shutdownNow();
    }
}

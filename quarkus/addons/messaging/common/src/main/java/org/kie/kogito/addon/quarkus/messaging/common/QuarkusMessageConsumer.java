package org.kie.kogito.addon.quarkus.messaging.common;

import java.util.Set;
import java.util.concurrent.ExecutorService;

import javax.inject.Inject;

import org.kie.kogito.Application;
import org.kie.kogito.Model;
import org.kie.kogito.event.EventExecutorServiceFactory;
import org.kie.kogito.event.EventReceiver;
import org.kie.kogito.event.impl.AbstractMessageConsumer;
import org.kie.kogito.process.Process;
import org.kie.kogito.process.ProcessService;

public abstract class QuarkusMessageConsumer<M extends Model, D> extends AbstractMessageConsumer<M, D> {

    @Inject
    Application application;

    @Inject
    ProcessService processService;

    @Inject
    EventExecutorServiceFactory factory;

    private ExecutorService executor;

    protected void init(Process<M> process, String trigger, Class<D> objectClass, EventReceiver eventReceiver, Set<String> correlation) {
        executor = factory.getExecutorService(trigger);
        init(application, process, trigger, eventReceiver, objectClass, processService, executor, correlation);
    }

    @javax.annotation.PreDestroy
    public void close() {
        executor.shutdownNow();
    }
}

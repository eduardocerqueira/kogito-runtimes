package org.kie.kogito.quarkus.workflow.jobs;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.context.ThreadContext;
import org.kie.kogito.services.jobs.impl.InMemoryJobService;

import io.quarkus.arc.DefaultBean;

@ApplicationScoped
@DefaultBean
public class QuarkusScheduledExecutorService extends ScheduledThreadPoolExecutor {

    @Inject
    ThreadContext context;

    public QuarkusScheduledExecutorService() {
        super(Integer.parseInt(System.getProperty(InMemoryJobService.IN_MEMORY_JOB_SERVICE_POOL_SIZE_PROPERTY, "10")));
    }

    @Override
    public ScheduledFuture<?> scheduleAtFixedRate(Runnable command, long initialDelay, long period, TimeUnit unit) {
        return super.scheduleAtFixedRate(context.contextualRunnable(command), initialDelay, period, unit);
    }

    @Override
    public ScheduledFuture<?> schedule(Runnable command, long delay, TimeUnit unit) {
        return super.schedule(context.contextualRunnable(command), delay, unit);
    }
}

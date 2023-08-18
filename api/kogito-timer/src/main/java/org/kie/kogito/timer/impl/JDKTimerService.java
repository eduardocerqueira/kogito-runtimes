package org.kie.kogito.timer.impl;

import java.util.Collection;
import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import org.kie.kogito.timer.InternalSchedulerService;
import org.kie.kogito.timer.Job;
import org.kie.kogito.timer.JobContext;
import org.kie.kogito.timer.JobHandle;
import org.kie.kogito.timer.SessionClock;
import org.kie.kogito.timer.TimerService;
import org.kie.kogito.timer.Trigger;

/**
 * A default Scheduler implementation that uses the
 * JDK built-in ScheduledThreadPoolExecutor as the
 * scheduler and the system clock as the clock.
 */
public class JDKTimerService
        implements
        TimerService,
        SessionClock,
        InternalSchedulerService {

    private final int size;

    private AtomicLong idCounter;

    protected ScheduledThreadPoolExecutor scheduler;

    protected TimerJobFactoryManager jobFactoryManager = DefaultTimerJobFactoryManager.instance;

    public JDKTimerService() {
        this(1);
    }

    public JDKTimerService(int size) {
        this.size = size;
        this.scheduler = new ScheduledThreadPoolExecutor(size);
        this.idCounter = new AtomicLong(0L);
    }

    public void setTimerJobFactoryManager(TimerJobFactoryManager timerJobFactoryManager) {
        this.jobFactoryManager = timerJobFactoryManager;
    }

    public TimerJobFactoryManager getTimerJobFactoryManager() {
        return this.jobFactoryManager;
    }

    /**
     * @inheritDoc
     */
    public long getCurrentTime() {
        return System.currentTimeMillis();
    }

    public void reset() {
        if (idCounter.get() != 0L) {
            this.scheduler.shutdownNow();
            this.scheduler = new ScheduledThreadPoolExecutor(size);
            this.idCounter.set(0L);
        }
    }

    @Override
    public void shutdown() {
        // forcing a shutdownNow instead of a regular shutdown()
        // to avoid delays on shutdown. This is an irreversible 
        // operation anyway, called on session dispose.
        this.scheduler.shutdownNow();
    }

    public JobHandle scheduleJob(Job job,
            JobContext ctx,
            Trigger trigger) {
        Date date = trigger.hasNextFireTime();
        if (date != null) {
            JDKJobHandle jobHandle = new JDKJobHandle(idCounter.getAndIncrement());

            TimerJobInstance jobInstance = jobFactoryManager.createTimerJobInstance(job,
                    ctx,
                    trigger,
                    jobHandle,
                    this);
            jobHandle.setTimerJobInstance(jobInstance);
            internalSchedule(jobInstance);

            return jobHandle;
        } else {
            return null;
        }
    }

    public void internalSchedule(TimerJobInstance timerJobInstance) {
        Date date = timerJobInstance.getTrigger().hasNextFireTime();
        Callable<Void> item = (Callable<Void>) timerJobInstance;

        JDKJobHandle jobHandle = (JDKJobHandle) timerJobInstance.getJobHandle();
        long then = date.getTime();
        long now = System.currentTimeMillis();
        ScheduledFuture<Void> future = null;
        if (then >= now) {
            future = scheduler.schedule(item,
                    then - now,
                    TimeUnit.MILLISECONDS);
        } else {
            future = scheduler.schedule(item,
                    0,
                    TimeUnit.MILLISECONDS);
        }

        jobHandle.setFuture(future);
        jobFactoryManager.addTimerJobInstance(timerJobInstance);
    }

    public boolean removeJob(JobHandle jobHandle) {
        jobHandle.setCancel(true);
        JDKJobHandle jdkJobHandle = (JDKJobHandle) jobHandle;
        jobFactoryManager.removeTimerJobInstance(jdkJobHandle.getTimerJobInstance());
        return this.scheduler.remove((Runnable) jdkJobHandle.getFuture());
    }

    public static class JDKJobHandle extends DefaultJobHandle {

        private static final long serialVersionUID = 510l;

        private ScheduledFuture<Void> future;

        public JDKJobHandle(long id) {
            super(id);
        }

        public ScheduledFuture<Void> getFuture() {
            return future;
        }

        public void setFuture(ScheduledFuture<Void> future) {
            this.future = future;
        }

    }

    public long getTimeToNextJob() {
        return 0;
    }

    public Collection<TimerJobInstance> getTimerJobInstances(long id) {
        return jobFactoryManager.getTimerJobInstances();
    }

}

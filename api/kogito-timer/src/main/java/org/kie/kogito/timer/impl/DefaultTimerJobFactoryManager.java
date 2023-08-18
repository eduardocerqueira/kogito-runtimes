package org.kie.kogito.timer.impl;

import java.util.Collection;
import java.util.Collections;

import org.kie.kogito.timer.InternalSchedulerService;
import org.kie.kogito.timer.Job;
import org.kie.kogito.timer.JobContext;
import org.kie.kogito.timer.JobHandle;
import org.kie.kogito.timer.Trigger;

public class DefaultTimerJobFactoryManager
        implements
        TimerJobFactoryManager {

    public static final DefaultTimerJobFactoryManager instance = new DefaultTimerJobFactoryManager();

    public TimerJobInstance createTimerJobInstance(Job job,
            JobContext ctx,
            Trigger trigger,
            JobHandle handle,
            InternalSchedulerService scheduler) {
        ctx.setJobHandle(handle);
        return new DefaultTimerJobInstance(job,
                ctx,
                trigger,
                handle,
                scheduler);
    }

    public Collection<TimerJobInstance> getTimerJobInstances() {
        return Collections.emptyList();
    }

    public void addTimerJobInstance(TimerJobInstance instance) {
    }

    public void removeTimerJobInstance(TimerJobInstance instance) {
    }
}

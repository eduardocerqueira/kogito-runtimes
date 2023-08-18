package org.kie.kogito.timer.impl;

import java.util.Collection;

import org.kie.kogito.timer.InternalSchedulerService;
import org.kie.kogito.timer.Job;
import org.kie.kogito.timer.JobContext;
import org.kie.kogito.timer.JobHandle;
import org.kie.kogito.timer.Trigger;

public interface TimerJobFactoryManager {
    TimerJobInstance createTimerJobInstance(Job job,
            JobContext ctx,
            Trigger trigger,
            JobHandle handle,
            InternalSchedulerService scheduler);

    void addTimerJobInstance(TimerJobInstance instance);

    void removeTimerJobInstance(TimerJobInstance instance);

    Collection<TimerJobInstance> getTimerJobInstances();
}

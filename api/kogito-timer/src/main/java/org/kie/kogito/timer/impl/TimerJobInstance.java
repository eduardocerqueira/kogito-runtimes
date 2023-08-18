package org.kie.kogito.timer.impl;

import org.kie.kogito.timer.Job;
import org.kie.kogito.timer.JobContext;
import org.kie.kogito.timer.JobHandle;
import org.kie.kogito.timer.Trigger;

public interface TimerJobInstance {
    public JobHandle getJobHandle();

    public Job getJob();

    public Trigger getTrigger();

    public JobContext getJobContext();

}

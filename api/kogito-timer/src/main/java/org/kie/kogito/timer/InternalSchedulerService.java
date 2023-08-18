package org.kie.kogito.timer;

import org.kie.kogito.timer.impl.TimerJobInstance;

public interface InternalSchedulerService {
    public void internalSchedule(TimerJobInstance timerJobInstance);
}

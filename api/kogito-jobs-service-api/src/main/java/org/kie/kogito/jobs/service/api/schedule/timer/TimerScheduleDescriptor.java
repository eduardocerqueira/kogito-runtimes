package org.kie.kogito.jobs.service.api.schedule.timer;

import org.kie.kogito.jobs.service.api.ScheduleDescriptor;

public class TimerScheduleDescriptor implements ScheduleDescriptor<TimerSchedule> {

    public static final String NAME = "timer";

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public Class<TimerSchedule> getType() {
        return TimerSchedule.class;
    }
}

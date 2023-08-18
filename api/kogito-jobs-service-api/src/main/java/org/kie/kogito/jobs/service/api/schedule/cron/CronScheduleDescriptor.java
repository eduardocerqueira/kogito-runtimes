package org.kie.kogito.jobs.service.api.schedule.cron;

import org.kie.kogito.jobs.service.api.ScheduleDescriptor;

public class CronScheduleDescriptor implements ScheduleDescriptor<CronSchedule> {

    public static final String NAME = "cron";

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public Class<CronSchedule> getType() {
        return CronSchedule.class;
    }
}

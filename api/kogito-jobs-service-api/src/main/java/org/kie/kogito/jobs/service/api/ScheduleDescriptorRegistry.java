package org.kie.kogito.jobs.service.api;

public final class ScheduleDescriptorRegistry extends AbstractDescriptorRegistry<ScheduleDescriptor> {

    private static final ScheduleDescriptorRegistry INSTANCE = new ScheduleDescriptorRegistry();

    private ScheduleDescriptorRegistry() {
        super(ScheduleDescriptor.class);
    }

    public static ScheduleDescriptorRegistry getInstance() {
        return INSTANCE;
    }
}

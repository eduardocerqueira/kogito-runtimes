package org.kie.kogito.jobs.service.api;

public interface ScheduleDescriptor<S extends Schedule> extends Descriptor {

    String getName();

    Class<S> getType();
}

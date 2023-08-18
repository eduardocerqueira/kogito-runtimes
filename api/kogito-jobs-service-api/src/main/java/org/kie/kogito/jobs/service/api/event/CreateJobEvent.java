package org.kie.kogito.jobs.service.api.event;

import org.kie.kogito.jobs.service.api.Job;

public class CreateJobEvent extends JobCloudEvent<Job> {

    public static final String TYPE = "job.create";

    public CreateJobEvent() {
        // Marshalling constructor.
        setType(TYPE);
    }

    @Override
    public void setType(String type) {
        assertExpectedType(type, TYPE);
        super.setType(type);
    }

    @Override
    public String toString() {
        return "CreateJobEvent{} " + super.toString();
    }

    public static Builder builder() {
        return new Builder(new CreateJobEvent());
    }

    public static class Builder extends AbstractJobCloudEventBuilder<Builder, Job, CreateJobEvent> {

        private Builder(CreateJobEvent current) {
            super(current);
        }

        public Builder job(Job job) {
            event.setData(job);
            return cast();
        }
    }
}

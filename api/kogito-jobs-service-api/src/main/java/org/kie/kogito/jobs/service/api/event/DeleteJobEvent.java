package org.kie.kogito.jobs.service.api.event;

import org.kie.kogito.jobs.service.api.JobLookupId;

public class DeleteJobEvent extends JobCloudEvent<JobLookupId> {

    public static final String TYPE = "job.delete";

    public DeleteJobEvent() {
        // Marshalling constructor.
        setType(TYPE);
    }

    @Override
    public void setType(String type) {
        assertExpectedType(type, TYPE);
        super.setType(type);
    }

    public static Builder builder() {
        return new Builder(new DeleteJobEvent());
    }

    public static class Builder extends AbstractJobCloudEventBuilder<Builder, JobLookupId, DeleteJobEvent> {

        private Builder(DeleteJobEvent event) {
            super(event);
        }

        public Builder lookupId(JobLookupId lookupId) {
            event.setData(lookupId);
            return this;
        }
    }
}

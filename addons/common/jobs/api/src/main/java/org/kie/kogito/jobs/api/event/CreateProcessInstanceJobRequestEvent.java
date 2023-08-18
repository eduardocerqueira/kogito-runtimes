package org.kie.kogito.jobs.api.event;

import org.kie.kogito.jobs.api.Job;

@Deprecated
public class CreateProcessInstanceJobRequestEvent extends ProcessInstanceContextJobCloudEvent<Job> {

    public static final String CREATE_PROCESS_INSTANCE_JOB_REQUEST = "CreateProcessInstanceJobRequest";

    public CreateProcessInstanceJobRequestEvent() {
        // marshalling constructor.
        setType(CREATE_PROCESS_INSTANCE_JOB_REQUEST);
    }

    @Override
    public void setType(String type) {
        assertExpectedType(type, CREATE_PROCESS_INSTANCE_JOB_REQUEST);
        super.setType(type);
    }

    public static Builder builder() {
        return new Builder(new CreateProcessInstanceJobRequestEvent());
    }

    @Override
    public String toString() {
        return "CreateProcessInstanceJobRequestEvent{} " + super.toString();
    }

    @Deprecated
    public static class Builder extends AbstractProcessInstanceContextJobCloudEventBuilder<Builder, Job, CreateProcessInstanceJobRequestEvent> {

        private Builder(CreateProcessInstanceJobRequestEvent current) {
            super(current);
        }

        public Builder job(Job job) {
            event.setData(job);
            return cast();
        }
    }

}

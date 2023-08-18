package org.kie.kogito.jobs.api.event;

@Deprecated
public class CancelJobRequestEvent extends ProcessInstanceContextJobCloudEvent<CancelJobRequestEvent.JobId> {

    public static final String CANCEL_JOB_REQUEST = "CancelJobRequest";

    public CancelJobRequestEvent() {
        // marshalling constructor.
        setType(CANCEL_JOB_REQUEST);
    }

    @Override
    public void setType(String type) {
        assertExpectedType(type, CANCEL_JOB_REQUEST);
        super.setType(type);
    }

    public static class JobId {

        private String id;

        public JobId() {
            // marshalling constructor.
        }

        public JobId(String id) {
            this.id = id;
        }

        public String getId() {
            return id;
        }
    }

    public static Builder builder() {
        return new Builder(new CancelJobRequestEvent());
    }

    @Override
    public String toString() {
        return "CancelJobRequestEvent{} " + super.toString();
    }

    @Deprecated
    public static class Builder extends AbstractProcessInstanceContextJobCloudEventBuilder<Builder, JobId, CancelJobRequestEvent> {

        private Builder(CancelJobRequestEvent event) {
            super(event);
        }

        public Builder jobId(String jobId) {
            event.setData(new CancelJobRequestEvent.JobId(jobId));
            return this;
        }
    }
}
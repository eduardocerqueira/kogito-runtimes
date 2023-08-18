package org.kie.kogito.incubation.processes.services.contexts;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class TaskMetaDataContext extends ProcessMetaDataContext {
    public static TaskMetaDataContext of(Policy policy) {
        return new TaskMetaDataContext(policy);
    }

    @JsonProperty
    private Policy policy;
    @JsonProperty
    private String phase;

    protected TaskMetaDataContext(Policy policy) {
        this.policy = policy;
    }

    protected TaskMetaDataContext() {
        this(new Policy());
    }

    public Policy policy() {
        return this.policy;
    }

    public String phase() {
        return phase;
    }

    void setPhase(String phase) {
        this.phase = phase;
    }

    void setPolicy(Policy policy) {
        this.policy = policy;
    }

}

package org.kie.kogito.jobs;

import java.util.UUID;

public class ProcessInstanceJobDescriptionBuilder {

    private String id;
    private String timerId;
    private ExpirationTime expirationTime;
    private Integer priority = ProcessInstanceJobDescription.DEFAULT_PRIORITY;
    private String processInstanceId;
    private String rootProcessInstanceId;
    private String processId;
    private String rootProcessId;
    private String nodeInstanceId;

    public ProcessInstanceJobDescriptionBuilder id(String id) {
        this.id = id;
        return this;
    }

    public ProcessInstanceJobDescriptionBuilder generateId() {
        return id(UUID.randomUUID().toString());
    }

    public ProcessInstanceJobDescriptionBuilder timerId(String timerId) {
        this.timerId = timerId;
        return this;
    }

    public ProcessInstanceJobDescriptionBuilder expirationTime(ExpirationTime expirationTime) {
        this.expirationTime = expirationTime;
        return this;
    }

    public ProcessInstanceJobDescriptionBuilder priority(Integer priority) {
        this.priority = priority;
        return this;
    }

    public ProcessInstanceJobDescriptionBuilder processInstanceId(String processInstanceId) {
        this.processInstanceId = processInstanceId;
        return this;
    }

    public ProcessInstanceJobDescriptionBuilder rootProcessInstanceId(String rootProcessInstanceId) {
        this.rootProcessInstanceId = rootProcessInstanceId;
        return this;
    }

    public ProcessInstanceJobDescriptionBuilder processId(String processId) {
        this.processId = processId;
        return this;
    }

    public ProcessInstanceJobDescriptionBuilder rootProcessId(String rootProcessId) {
        this.rootProcessId = rootProcessId;
        return this;
    }

    public ProcessInstanceJobDescriptionBuilder nodeInstanceId(String nodeInstanceId) {
        this.nodeInstanceId = nodeInstanceId;
        return this;
    }

    public ProcessInstanceJobDescription build() {
        return new ProcessInstanceJobDescription(id, timerId, expirationTime, priority, processInstanceId, rootProcessInstanceId, processId, rootProcessId, nodeInstanceId);
    }
}
package org.kie.kogito.jobs;

import static java.util.Objects.requireNonNull;

public class ProcessInstanceJobDescription implements JobDescription {

    public static final Integer DEFAULT_PRIORITY = 5;

    private final String id;
    private final String timerId;
    private final ExpirationTime expirationTime;
    private final Integer priority;
    private final String processInstanceId;
    private final String rootProcessInstanceId;
    private final String processId;
    private final String rootProcessId;
    private final String nodeInstanceId;

    public ProcessInstanceJobDescription(String id,
            String timerId,
            ExpirationTime expirationTime,
            Integer priority,
            String processInstanceId,
            String rootProcessInstanceId,
            String processId,
            String rootProcessId,
            String nodeInstanceId) {
        this.id = requireNonNull(id);
        this.timerId = requireNonNull(timerId);
        this.expirationTime = requireNonNull(expirationTime);
        this.priority = requireNonNull(priority);
        this.processInstanceId = requireNonNull(processInstanceId);
        this.rootProcessInstanceId = rootProcessInstanceId;
        this.processId = processId;
        this.rootProcessId = rootProcessId;
        this.nodeInstanceId = nodeInstanceId;
    }

    @Override
    public String id() {
        return id;
    }

    public String timerId() {
        return timerId;
    }

    @Override
    public ExpirationTime expirationTime() {
        return expirationTime;
    }

    @Override
    public Integer priority() {
        return priority;
    }

    public String processInstanceId() {
        return processInstanceId;
    }

    public String rootProcessInstanceId() {
        return rootProcessInstanceId;
    }

    public String processId() {
        return processId;
    }

    public String rootProcessId() {
        return rootProcessId;
    }

    public String nodeInstanceId() {
        return nodeInstanceId;
    }

    public static ProcessInstanceJobDescriptionBuilder builder() {
        return new ProcessInstanceJobDescriptionBuilder();
    }

    @Override
    public String toString() {
        return "ProcessInstanceJobDescription{" +
                "id='" + id + '\'' +
                ", timerId=" + timerId + '\'' +
                ", expirationTime=" + expirationTime +
                ", priority=" + priority +
                ", processInstanceId='" + processInstanceId + '\'' +
                ", rootProcessInstanceId='" + rootProcessInstanceId + '\'' +
                ", processId='" + processId + '\'' +
                ", rootProcessId='" + rootProcessId + '\'' +
                ", nodeInstanceId='" + nodeInstanceId + '\'' +
                '}';
    }
}

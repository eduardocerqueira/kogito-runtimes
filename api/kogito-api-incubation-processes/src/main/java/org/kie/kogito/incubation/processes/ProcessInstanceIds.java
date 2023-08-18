package org.kie.kogito.incubation.processes;

public class ProcessInstanceIds {
    private final LocalProcessId processId;

    public ProcessInstanceIds(LocalProcessId processId) {
        this.processId = processId;
    }

    public ProcessInstanceId get(String processInstanceId) {
        return new ProcessInstanceId(processId, processInstanceId);
    }
}

package org.kie.kogito.incubation.processes;

public class SignalIds {
    private final ProcessInstanceId processInstanceId;

    public SignalIds(ProcessInstanceId processInstanceId) {
        this.processInstanceId = processInstanceId;
    }

    public SignalId get(String signalId) {
        return new SignalId(this.processInstanceId, signalId);
    }

}

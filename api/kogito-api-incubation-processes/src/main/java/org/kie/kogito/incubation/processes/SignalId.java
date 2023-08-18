package org.kie.kogito.incubation.processes;

import org.kie.kogito.incubation.common.LocalUriId;

public class SignalId extends LocalUriId {
    public static final String PREFIX = "signals";
    private final ProcessInstanceId processInstanceId;
    private final String signalId;

    public SignalId(ProcessInstanceId processInstanceId, String signalId) {
        super(processInstanceId.asLocalUri().append(PREFIX).append(signalId));
        this.processInstanceId = processInstanceId;
        this.signalId = signalId;
    }

    public String signalId() {
        return signalId;
    }

    public ProcessInstanceId processInstanceId() {
        return processInstanceId;
    }
}

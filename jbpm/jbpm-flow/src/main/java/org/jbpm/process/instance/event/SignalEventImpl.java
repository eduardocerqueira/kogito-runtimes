package org.jbpm.process.instance.event;

import org.kie.api.event.process.SignalEvent;
import org.kie.api.runtime.KieRuntime;
import org.kie.api.runtime.process.NodeInstance;
import org.kie.api.runtime.process.ProcessInstance;

public class SignalEventImpl extends ProcessEvent implements SignalEvent {

    private static final long serialVersionUID = 1L;
    private NodeInstance nodeInstance;
    private String signalName;
    private Object signalObject;

    public SignalEventImpl(ProcessInstance instance, KieRuntime kruntime, NodeInstance nodeInstance,
            String signalName, Object signalObject, String identity) {
        super(instance, kruntime, identity);
        this.nodeInstance = nodeInstance;
        this.signalName = signalName;
        this.signalObject = signalObject;
    }

    @Override
    public NodeInstance getNodeInstance() {
        return nodeInstance;
    }

    @Override
    public String getSignalName() {
        return signalName;
    }

    @Override
    public Object getSignal() {
        return signalObject;
    }

    @Override
    public String toString() {
        return "SignalEventImpl [nodeInstance=" + nodeInstance + ", signalName=" + signalName + ", signalObject=" +
                signalObject + "]";
    }
}

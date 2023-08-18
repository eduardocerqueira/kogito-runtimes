package org.jbpm.process.instance.event;

import org.kie.api.event.process.SLAViolatedEvent;
import org.kie.api.runtime.KieRuntime;
import org.kie.api.runtime.process.NodeInstance;
import org.kie.api.runtime.process.ProcessInstance;

public class SLAViolatedEventImpl extends ProcessEvent implements SLAViolatedEvent {

    private static final long serialVersionUID = 510l;
    private NodeInstance nodeInstance;

    public SLAViolatedEventImpl(final ProcessInstance instance, KieRuntime kruntime, String identity) {
        super(instance, kruntime, identity);
    }

    public SLAViolatedEventImpl(ProcessInstance instance, NodeInstance nodeInstance, KieRuntime kruntime, String identity) {
        super(instance, kruntime, identity);
        this.nodeInstance = nodeInstance;
    }

    @Override
    public String toString() {
        return "==>[SLAViolatedEvent(name=" + getProcessInstance().getProcessName() + "; id=" + getProcessInstance().getProcessId() + ")]";
    }

    @Override
    public NodeInstance getNodeInstance() {
        return nodeInstance;
    }

}

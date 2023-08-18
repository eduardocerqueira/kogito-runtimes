package org.jbpm.process.instance.event;

import org.kie.api.event.process.ProcessNodeLeftEvent;
import org.kie.api.runtime.KieRuntime;
import org.kie.api.runtime.process.NodeInstance;
import org.kie.kogito.internal.process.runtime.KogitoNodeInstance;

public class KogitoProcessNodeLeftEventImpl extends ProcessEvent implements ProcessNodeLeftEvent {

    private static final long serialVersionUID = 510l;

    private NodeInstance nodeInstance;

    public KogitoProcessNodeLeftEventImpl(NodeInstance nodeInstance, KieRuntime kruntime, String identity) {
        super(nodeInstance.getProcessInstance(), kruntime, identity);
        this.nodeInstance = nodeInstance;
    }

    public NodeInstance getNodeInstance() {
        return nodeInstance;
    }

    @Override
    public String toString() {
        return "==>[ProcessNodeLeft(nodeId=" + nodeInstance.getNodeId() + "; id=" + ((KogitoNodeInstance) nodeInstance).getStringId()
                + "; nodeName=" + getNodeInstance().getNodeName() + "; processName=" + getProcessInstance().getProcessName() + "; processId=" + getProcessInstance().getProcessId() + ")]";
    }
}

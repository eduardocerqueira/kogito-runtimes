package org.jbpm.process.instance.event;

import org.kie.api.event.process.ProcessNodeTriggeredEvent;
import org.kie.api.runtime.KieRuntime;
import org.kie.api.runtime.process.NodeInstance;
import org.kie.kogito.internal.process.runtime.KogitoNodeInstance;

public class KogitoProcessNodeTriggeredEventImpl extends ProcessEvent implements ProcessNodeTriggeredEvent {

    private static final long serialVersionUID = 510l;

    private NodeInstance nodeInstance;

    public KogitoProcessNodeTriggeredEventImpl(NodeInstance nodeInstance, KieRuntime kruntime, String identity) {
        super(nodeInstance.getProcessInstance(), kruntime, identity);
        this.nodeInstance = nodeInstance;
    }

    public NodeInstance getNodeInstance() {
        return nodeInstance;
    }

    @Override
    public String toString() {
        return "==>[ProcessNodeTriggered(nodeId=" + nodeInstance.getNodeId() + "; id=" + ((KogitoNodeInstance) nodeInstance).getStringId()
                + "; nodeName=" + getNodeInstance().getNodeName() + "; processName=" + getProcessInstance().getProcessName() + "; processId=" + getProcessInstance().getProcessId() + ")]";
    }
}

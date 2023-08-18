package org.jbpm.process.instance.event;

import org.kie.api.event.process.MessageEvent;
import org.kie.api.runtime.KieRuntime;
import org.kie.api.runtime.process.NodeInstance;
import org.kie.api.runtime.process.ProcessInstance;

public class MessageEventImpl extends ProcessEvent implements MessageEvent {

    private static final long serialVersionUID = 1L;
    private NodeInstance nodeInstance;
    private String messageName;
    private Object messageObject;

    public MessageEventImpl(ProcessInstance instance, KieRuntime kruntime, NodeInstance nodeInstance,
            String messageName, Object messageObject, String identity) {
        super(instance, kruntime, identity);
        this.nodeInstance = nodeInstance;
        this.messageName = messageName;
        this.messageObject = messageObject;
    }

    @Override
    public NodeInstance getNodeInstance() {
        return nodeInstance;
    }

    @Override
    public String getMessageName() {
        return messageName;
    }

    @Override
    public Object getMessage() {
        return messageObject;
    }

    @Override
    public String toString() {
        return "MessageEventImpl [nodeInstance=" + nodeInstance + ", messageName=" + messageName + ", messageObject=" +
                messageObject + "]";
    }
}

package org.jbpm.process.core.correlation;

import java.io.Serializable;

public class Message implements Serializable {

    private static final long serialVersionUID = -2796150124974362714L;

    private String messageRef;
    private String messageName;
    private String messageType;

    public Message(String messageRef, String messageName, String messageType) {
        this.messageRef = messageRef;
        this.messageName = messageName;
        this.messageType = messageType;
    }

    public String getMessageRef() {
        return messageRef;
    }

    public String getMessageName() {
        return messageName;
    }

    public String getMessageType() {
        return messageType;
    }

}
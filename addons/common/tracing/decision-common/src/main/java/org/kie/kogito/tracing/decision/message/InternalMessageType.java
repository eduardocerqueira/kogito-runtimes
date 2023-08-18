package org.kie.kogito.tracing.decision.message;

import org.kie.kogito.tracing.event.message.MessageLevel;

public enum InternalMessageType {
    DMN_MODEL_NOT_FOUND(MessageLevel.ERROR, "DMN model not found"),
    NO_EXECUTION_STEP_HIERARCHY(MessageLevel.WARNING, "Can't build execution step hierarchy"),
    NOT_ENOUGH_DATA(MessageLevel.ERROR, "Not enough data to build a valid TraceEvent");

    private final MessageLevel level;
    private final String text;

    InternalMessageType(MessageLevel level, String text) {
        this.level = level;
        this.text = text;
    }

    public MessageLevel getLevel() {
        return level;
    }

    public String getText() {
        return text;
    }
}

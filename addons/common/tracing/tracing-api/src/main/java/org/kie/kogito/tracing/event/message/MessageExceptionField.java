package org.kie.kogito.tracing.event.message;

import com.fasterxml.jackson.annotation.JsonInclude;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

public class MessageExceptionField {

    private String className;
    private String message;
    @JsonInclude(NON_NULL)
    private MessageExceptionField cause;

    private MessageExceptionField() {
    }

    public MessageExceptionField(String className, String message, MessageExceptionField cause) {
        this.className = className;
        this.message = message;
        this.cause = cause;
    }

    public String getClassName() {
        return className;
    }

    public String getMessage() {
        return message;
    }

    public MessageExceptionField getCause() {
        return cause;
    }
}

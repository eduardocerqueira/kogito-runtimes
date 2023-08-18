package org.kie.kogito.tracing.event.message;

import com.fasterxml.jackson.annotation.JsonInclude;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

public class MessageFEELEvent {

    private MessageFEELEventSeverity severity;
    private String message;
    @JsonInclude(NON_NULL)
    private Integer line;
    @JsonInclude(NON_NULL)
    private Integer column;
    @JsonInclude(NON_NULL)
    private MessageExceptionField sourceException;

    private MessageFEELEvent() {
    }

    public MessageFEELEvent(MessageFEELEventSeverity severity, String message, Integer line, Integer column, MessageExceptionField sourceException) {
        this.severity = severity;
        this.message = message;
        this.line = line == null || line < 0 ? null : line;
        this.column = column == null || column < 0 ? null : column;
        this.sourceException = sourceException;
    }

    public MessageFEELEventSeverity getSeverity() {
        return severity;
    }

    public String getMessage() {
        return message;
    }

    public Integer getLine() {
        return line;
    }

    public Integer getColumn() {
        return column;
    }

    public MessageExceptionField getSourceException() {
        return sourceException;
    }
}

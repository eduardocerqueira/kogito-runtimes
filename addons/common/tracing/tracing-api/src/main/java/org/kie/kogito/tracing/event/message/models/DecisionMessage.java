package org.kie.kogito.tracing.event.message.models;

import org.kie.kogito.ModelDomain;
import org.kie.kogito.tracing.event.message.Message;
import org.kie.kogito.tracing.event.message.MessageCategory;
import org.kie.kogito.tracing.event.message.MessageExceptionField;
import org.kie.kogito.tracing.event.message.MessageFEELEvent;
import org.kie.kogito.tracing.event.message.MessageLevel;

import com.fasterxml.jackson.annotation.JsonInclude;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonInclude(NON_NULL)
public final class DecisionMessage extends Message {

    private MessageFEELEvent feelEvent;

    private DecisionMessage() {
        // needed for serialization
    }

    public DecisionMessage(MessageLevel level, MessageCategory category, String type, String sourceId, String text, MessageFEELEvent feelEvent, MessageExceptionField exception) {
        super(level, category, type, sourceId, text, exception, ModelDomain.DECISION);
        this.feelEvent = feelEvent;
    }

    public MessageFEELEvent getFeelEvent() {
        return feelEvent;
    }

}

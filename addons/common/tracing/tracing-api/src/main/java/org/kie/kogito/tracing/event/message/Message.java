package org.kie.kogito.tracing.event.message;

import org.kie.kogito.ModelDomain;
import org.kie.kogito.tracing.event.message.models.DecisionMessage;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * <code>Message</code> to be eventually extended by model-specific implementations
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "@type", visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = DecisionMessage.class, name = "DECISION"),
})
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class Message {

    private MessageLevel level;
    private MessageCategory category;
    private String type;
    private String sourceId;
    private String text;
    private MessageExceptionField exception;
    @JsonProperty("@type")
    private ModelDomain modelDomain;

    protected Message() {
        // needed for serialization
    }

    public Message(MessageLevel level, MessageCategory category, String type, String sourceId, String text, MessageExceptionField exception,
            ModelDomain modelDomain) {
        this.level = level;
        this.category = category;
        this.type = type;
        this.sourceId = sourceId;
        this.text = text;
        this.exception = exception;
        this.modelDomain = modelDomain;
    }

    public MessageLevel getLevel() {
        return level;
    }

    public MessageCategory getCategory() {
        return category;
    }

    public String getType() {
        return type;
    }

    public String getSourceId() {
        return sourceId;
    }

    public String getText() {
        return text;
    }

    public MessageExceptionField getException() {
        return exception;
    }
}

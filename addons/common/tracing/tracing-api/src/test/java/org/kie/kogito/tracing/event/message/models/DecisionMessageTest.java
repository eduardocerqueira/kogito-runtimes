package org.kie.kogito.tracing.event.message.models;

import java.util.Random;

import org.junit.jupiter.api.Test;
import org.kie.kogito.tracing.event.TracingTestUtils;
import org.kie.kogito.tracing.event.message.Message;
import org.kie.kogito.tracing.event.message.MessageCategory;
import org.kie.kogito.tracing.event.message.MessageExceptionField;
import org.kie.kogito.tracing.event.message.MessageFEELEvent;
import org.kie.kogito.tracing.event.message.MessageFEELEventSeverity;
import org.kie.kogito.tracing.event.message.MessageLevel;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.assertj.core.api.Assertions.assertThat;

public class DecisionMessageTest {

    @Test
    public void testDeserialization() throws JsonProcessingException {
        String toRead = TracingTestUtils.readResourceAsString("/decisionmessage.json");
        Message retrieved = new ObjectMapper().readValue(toRead, Message.class);
        assertThat(retrieved).isInstanceOf(DecisionMessage.class);
    }

    @Test
    public void testSerialization() throws JsonProcessingException {
        Message message = getDecisionMessage(new Random().nextInt(5));
        String retrieved = new ObjectMapper().writeValueAsString(message);
        assertThat(retrieved).isNotNull();
        System.out.println(retrieved);
    }

    public static Message getDecisionMessage(int id) {
        return new DecisionMessage(getMessageLevel(),
                getMessageCategory(),
                "type-" + id,
                "sourceId-" + id,
                "text-" + id,
                getMessageFEELEvent(id),
                getMessageExceptionField(id));
    }

    private static MessageFEELEvent getMessageFEELEvent(int id) {
        return new MessageFEELEvent(getMessageFEELEventSeverity(),
                "message-" + id,
                id,
                id,
                getMessageExceptionField(id));
    }

    private static MessageExceptionField getMessageExceptionField(int id) {
        return new MessageExceptionField("className-" + id, "message-" + id, null);
    }

    private static MessageCategory getMessageCategory() {
        return MessageCategory.values()[new Random().nextInt(MessageCategory.values().length)];
    }

    private static MessageLevel getMessageLevel() {
        return MessageLevel.values()[new Random().nextInt(MessageLevel.values().length)];
    }

    private static MessageFEELEventSeverity getMessageFEELEventSeverity() {
        return MessageFEELEventSeverity.values()[new Random().nextInt(MessageFEELEventSeverity.values().length)];
    }
}

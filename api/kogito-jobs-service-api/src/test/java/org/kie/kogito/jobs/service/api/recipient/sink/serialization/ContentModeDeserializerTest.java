package org.kie.kogito.jobs.service.api.recipient.sink.serialization;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kie.kogito.jobs.service.api.recipient.sink.SinkRecipient;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class ContentModeDeserializerTest {

    @Mock
    private JsonParser parser;

    @Mock
    private DeserializationContext context;

    private ContentModeDeserializer deserializer = new ContentModeDeserializer();

    @Test
    void deserializeStructured() throws IOException {
        doReturn("structured").when(parser).getText();
        assertThat(deserializer.deserialize(parser, context)).isEqualTo(SinkRecipient.ContentMode.STRUCTURED);
    }

    @Test
    void deserializeBinary() throws IOException {
        doReturn("binary").when(parser).getText();
        assertThat(deserializer.deserialize(parser, context)).isEqualTo(SinkRecipient.ContentMode.BINARY);
    }

    @Test
    void deserializeUnknown() throws IOException {
        doReturn("unknown").when(parser).getText();
        assertThatThrownBy(() -> deserializer.deserialize(parser, context))
                .hasMessage("Invalid content mode: unknown");
    }
}

package org.kie.kogito.jobs.service.api.recipient.sink.serialization;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kie.kogito.jobs.service.api.recipient.sink.SinkRecipient;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ContentModeSerializerTest {

    @Mock
    private JsonGenerator generator;

    @Mock
    private SerializerProvider provider;

    private ContentModeSerializer serializer = new ContentModeSerializer();

    @Test
    void serializeStructured() throws IOException {
        serializer.serialize(SinkRecipient.ContentMode.STRUCTURED, generator, provider);
        verify(generator).writeString("structured");
    }

    @Test
    void serializeBinary() throws IOException {
        serializer.serialize(SinkRecipient.ContentMode.BINARY, generator, provider);
        verify(generator).writeString("binary");
    }
}

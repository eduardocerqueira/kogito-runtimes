package org.kie.kogito.jobs.service.api.recipient.sink.serialization;

import java.io.IOException;

import org.kie.kogito.jobs.service.api.recipient.sink.SinkRecipient;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class ContentModeSerializer extends JsonSerializer<SinkRecipient.ContentMode> {

    @Override
    public void serialize(SinkRecipient.ContentMode contentMode, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString(contentMode.toString());
    }
}

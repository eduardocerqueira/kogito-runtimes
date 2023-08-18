package org.kie.kogito.jobs.service.api.recipient.sink.serialization;

import java.io.IOException;

import org.kie.kogito.jobs.service.api.recipient.sink.SinkRecipient;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class ContentModeDeserializer extends JsonDeserializer<SinkRecipient.ContentMode> {

    @Override
    public SinkRecipient.ContentMode deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        return SinkRecipient.ContentMode.parse(jsonParser.getText());
    }
}

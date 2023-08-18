package org.kie.kogito.event.cloudevents;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import io.cloudevents.SpecVersion;

public class SpecVersionDeserializer extends JsonDeserializer<SpecVersion> {

    @Override
    public SpecVersion deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        return SpecVersion.parse(jsonParser.getText());
    }
}

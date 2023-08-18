package org.kie.kogito.jobs.service.api.serlialization;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import io.cloudevents.SpecVersion;

public class SpecVersionSerializer extends JsonSerializer<SpecVersion> {

    @Override
    public void serialize(SpecVersion specVersion, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString(specVersion.toString());
    }
}

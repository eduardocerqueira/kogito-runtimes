package org.kie.kogito.jobs.service.api.serialization;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kie.kogito.jobs.service.api.serlialization.SpecVersionSerializer;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;

import io.cloudevents.SpecVersion;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class SpecVersionSerializerTest {

    @Mock
    private JsonGenerator generator;

    @Mock
    private SerializerProvider provider;

    private SpecVersionSerializer serializer = new SpecVersionSerializer();

    @Test
    void serializeV1() throws IOException {
        serializer.serialize(SpecVersion.V1, generator, provider);
        verify(generator).writeString("1.0");
    }

    @Test
    void serializeV03() throws IOException {
        serializer.serialize(SpecVersion.V03, generator, provider);
        verify(generator).writeString("0.3");
    }
}

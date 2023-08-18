package org.kie.kogito.jobs.service.api.serialization;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kie.kogito.jobs.service.api.serlialization.SpecVersionDeserializer;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;

import io.cloudevents.SpecVersion;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class SpecVersionDeserializerTest {

    @Mock
    private JsonParser parser;

    @Mock
    private DeserializationContext context;

    private SpecVersionDeserializer deserializer = new SpecVersionDeserializer();

    @Test
    void deserializeV1() throws IOException {
        doReturn("1.0").when(parser).getText();
        assertThat(deserializer.deserialize(parser, context)).isEqualTo(SpecVersion.V1);
    }

    @Test
    void deserializeV03() throws IOException {
        doReturn("0.3").when(parser).getText();
        assertThat(deserializer.deserialize(parser, context)).isEqualTo(SpecVersion.V03);
    }

    @Test
    void deserializeUnknown() throws IOException {
        doReturn("unknown").when(parser).getText();
        assertThatThrownBy(() -> deserializer.deserialize(parser, context))
                .hasMessage("Invalid specversion: unknown");
    }
}

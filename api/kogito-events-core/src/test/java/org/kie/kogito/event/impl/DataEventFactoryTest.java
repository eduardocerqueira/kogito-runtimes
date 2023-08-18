package org.kie.kogito.event.impl;

import java.io.IOException;
import java.net.URI;

import org.junit.jupiter.api.Test;
import org.kie.kogito.event.DataEvent;
import org.kie.kogito.event.DataEventFactory;
import org.kie.kogito.jackson.utils.ObjectMapperFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.cloudevents.core.builder.CloudEventBuilder;
import io.cloudevents.jackson.JsonCloudEventData;

import static org.assertj.core.api.Assertions.assertThat;

public class DataEventFactoryTest {

    @Test
    void testIsJacksonSerializable() throws JsonProcessingException, IOException {
        ObjectMapper objectMapper = ObjectMapperFactory.get();
        CloudEventBuilder builder =
                CloudEventBuilder.v1().withId("1").withType("type").withSource(URI.create("/pepe/pepa")).withData(JsonCloudEventData.wrap(objectMapper.createObjectNode().put("name", "Javierito")))
                        .withExtension("pepe", "pepa");
        DataEvent<JsonNode> dataEvent = DataEventFactory.from(builder.build(), ced -> objectMapper.readTree(ced.toBytes()));
        JsonNode deserialized = objectMapper.readTree(objectMapper.writeValueAsBytes(dataEvent.asCloudEvent(JsonCloudEventData::wrap)));
        System.out.println(deserialized);
        JsonNode data = deserialized.get("data");

        assertThat(data).isNotNull();
        assertThat(data.get("name").asText()).isEqualTo("Javierito");
        assertThat(deserialized.get("type").asText()).isEqualTo("type");
        assertThat(deserialized.get("pepe").asText()).isEqualTo("pepa");

    }
}

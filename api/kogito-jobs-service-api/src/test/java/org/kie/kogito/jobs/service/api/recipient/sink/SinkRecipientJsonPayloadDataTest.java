package org.kie.kogito.jobs.service.api.recipient.sink;

import java.util.Objects;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import static org.assertj.core.api.Assertions.assertThat;

class SinkRecipientJsonPayloadDataTest {

    public static final String PROPERTY_NAME = "PROPERTY_NAME";
    public static final String PROPERTY_VALUE = "PROPERTY_VALUE";

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void getData() {
        ObjectNode json = objectMapper.createObjectNode().put(PROPERTY_NAME, PROPERTY_VALUE);
        SinkRecipientJsonPayloadData payloadData = SinkRecipientJsonPayloadData.from(json);
        assertThat(payloadData.getData()).isEqualTo(json);
    }

    @Test
    void equalsMethod() {
        ObjectNode json1 = objectMapper.createObjectNode().put(PROPERTY_NAME, PROPERTY_VALUE);
        ObjectNode json2 = objectMapper.createObjectNode().put(PROPERTY_NAME, PROPERTY_VALUE);
        SinkRecipientJsonPayloadData payloadData1 = SinkRecipientJsonPayloadData.from(json1);
        SinkRecipientJsonPayloadData payloadData2 = SinkRecipientJsonPayloadData.from(json2);
        assertThat(Objects.equals(payloadData1, payloadData2)).isTrue();
        json2.remove(PROPERTY_NAME);
        assertThat(Objects.equals(payloadData1, payloadData2)).isFalse();
    }

    @Test
    void hashCodeMethod() {
        ObjectNode json = objectMapper.createObjectNode().put(PROPERTY_NAME, PROPERTY_VALUE);
        SinkRecipientJsonPayloadData payloadData = SinkRecipientJsonPayloadData.from(json);
        assertThat(payloadData.hashCode()).isEqualTo(Objects.hash(json));
    }
}

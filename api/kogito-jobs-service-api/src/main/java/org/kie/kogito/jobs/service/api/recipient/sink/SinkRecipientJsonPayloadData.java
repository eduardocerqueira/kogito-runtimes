package org.kie.kogito.jobs.service.api.recipient.sink;

import java.util.Objects;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

@Schema(allOf = { SinkRecipientPayloadData.class })
public class SinkRecipientJsonPayloadData extends SinkRecipientPayloadData<JsonNode> {

    @JsonProperty("data")
    private JsonNode dataJson;

    public SinkRecipientJsonPayloadData() {
        // Marshalling constructor.
    }

    private SinkRecipientJsonPayloadData(JsonNode data) {
        this.dataJson = data;
    }

    @Override
    public JsonNode getData() {
        return dataJson;
    }

    public static SinkRecipientJsonPayloadData from(JsonNode data) {
        return new SinkRecipientJsonPayloadData(data);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SinkRecipientJsonPayloadData that = (SinkRecipientJsonPayloadData) o;
        return Objects.equals(dataJson, that.dataJson);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dataJson);
    }
}

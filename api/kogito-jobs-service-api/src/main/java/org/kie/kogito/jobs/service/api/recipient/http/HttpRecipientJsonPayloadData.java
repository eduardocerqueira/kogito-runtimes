package org.kie.kogito.jobs.service.api.recipient.http;

import java.util.Objects;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

@Schema(allOf = { HttpRecipientPayloadData.class })
public class HttpRecipientJsonPayloadData extends HttpRecipientPayloadData<JsonNode> {

    @JsonProperty("data")
    private JsonNode dataJson;

    public HttpRecipientJsonPayloadData() {
        // Marshalling constructor.
    }

    private HttpRecipientJsonPayloadData(JsonNode data) {
        this.dataJson = data;
    }

    @Override
    public JsonNode getData() {
        return dataJson;
    }

    public static HttpRecipientJsonPayloadData from(JsonNode data) {
        return new HttpRecipientJsonPayloadData(data);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        HttpRecipientJsonPayloadData that = (HttpRecipientJsonPayloadData) o;
        return Objects.equals(dataJson, that.dataJson);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dataJson);
    }
}

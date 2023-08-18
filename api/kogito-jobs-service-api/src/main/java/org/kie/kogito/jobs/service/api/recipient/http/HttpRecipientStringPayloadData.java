package org.kie.kogito.jobs.service.api.recipient.http;

import java.util.Objects;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import com.fasterxml.jackson.annotation.JsonProperty;

@Schema(allOf = { HttpRecipientPayloadData.class })
public class HttpRecipientStringPayloadData extends HttpRecipientPayloadData<String> {

    @JsonProperty("data")
    private String dataString;

    public HttpRecipientStringPayloadData() {
        // Marshalling constructor.
    }

    private HttpRecipientStringPayloadData(String data) {
        this.dataString = data;
    }

    public String getData() {
        return dataString;
    }

    public static HttpRecipientStringPayloadData from(String data) {
        return new HttpRecipientStringPayloadData(data);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof HttpRecipientStringPayloadData)) {
            return false;
        }
        HttpRecipientStringPayloadData that = (HttpRecipientStringPayloadData) o;
        return Objects.equals(dataString, that.dataString);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dataString);
    }
}

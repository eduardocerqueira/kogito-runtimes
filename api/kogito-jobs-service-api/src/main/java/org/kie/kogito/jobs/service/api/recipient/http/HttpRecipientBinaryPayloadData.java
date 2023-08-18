package org.kie.kogito.jobs.service.api.recipient.http;

import java.util.Arrays;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import com.fasterxml.jackson.annotation.JsonProperty;

@Schema(allOf = { HttpRecipientPayloadData.class })
public class HttpRecipientBinaryPayloadData extends HttpRecipientPayloadData<byte[]> {

    @JsonProperty("data")
    private byte[] dataBytes;

    public HttpRecipientBinaryPayloadData() {
        // Marshalling constructor.
    }

    private HttpRecipientBinaryPayloadData(byte[] data) {
        this.dataBytes = data;
    }

    @Override
    public byte[] getData() {
        return dataBytes;
    }

    public static HttpRecipientBinaryPayloadData from(byte[] data) {
        return new HttpRecipientBinaryPayloadData(data);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof HttpRecipientBinaryPayloadData)) {
            return false;
        }
        HttpRecipientBinaryPayloadData that = (HttpRecipientBinaryPayloadData) o;
        return Arrays.equals(dataBytes, that.dataBytes);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(dataBytes);
    }
}

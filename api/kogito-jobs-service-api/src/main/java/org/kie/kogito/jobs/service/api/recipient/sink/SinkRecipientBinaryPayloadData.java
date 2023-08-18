package org.kie.kogito.jobs.service.api.recipient.sink;

import java.util.Arrays;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import com.fasterxml.jackson.annotation.JsonProperty;

@Schema(allOf = { SinkRecipientPayloadData.class })
public class SinkRecipientBinaryPayloadData extends SinkRecipientPayloadData<byte[]> {

    @JsonProperty("data")
    private byte[] dataBytes;

    public SinkRecipientBinaryPayloadData() {
        // Marshalling constructor.
    }

    private SinkRecipientBinaryPayloadData(byte[] data) {
        this.dataBytes = data;
    }

    @Override
    public byte[] getData() {
        return dataBytes;
    }

    public static SinkRecipientBinaryPayloadData from(byte[] data) {
        return new SinkRecipientBinaryPayloadData(data);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SinkRecipientBinaryPayloadData that = (SinkRecipientBinaryPayloadData) o;
        return Arrays.equals(dataBytes, that.dataBytes);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(dataBytes);
    }
}

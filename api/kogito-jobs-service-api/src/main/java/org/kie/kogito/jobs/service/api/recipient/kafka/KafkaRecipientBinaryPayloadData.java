package org.kie.kogito.jobs.service.api.recipient.kafka;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import com.fasterxml.jackson.annotation.JsonProperty;

@Schema(allOf = { KafkaRecipientPayloadData.class })
public class KafkaRecipientBinaryPayloadData extends KafkaRecipientPayloadData<byte[]> {

    @JsonProperty("data")
    private byte[] dataBytes;

    public KafkaRecipientBinaryPayloadData() {
        // Marshalling constructor.
    }

    private KafkaRecipientBinaryPayloadData(byte[] data) {
        this.dataBytes = data;
    }

    @Override
    public byte[] getData() {
        return dataBytes;
    }

    public static KafkaRecipientBinaryPayloadData from(byte[] data) {
        return new KafkaRecipientBinaryPayloadData(data);
    }
}

package org.kie.kogito.jobs.service.api.recipient.kafka;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import com.fasterxml.jackson.annotation.JsonProperty;

@Schema(allOf = { KafkaRecipientStringPayloadData.class })
public class KafkaRecipientStringPayloadData extends KafkaRecipientPayloadData<String> {

    @JsonProperty("data")
    private String dataString;

    public KafkaRecipientStringPayloadData() {
        // Marshalling constructor.
    }

    private KafkaRecipientStringPayloadData(String data) {
        this.dataString = data;
    }

    @Override
    public String getData() {
        return dataString;
    }

    public static KafkaRecipientStringPayloadData from(String data) {
        return new KafkaRecipientStringPayloadData(data);
    }
}

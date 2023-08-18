package org.kie.kogito.jobs.service.api.recipient.kafka;

import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.DiscriminatorMapping;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.media.SchemaProperty;
import org.kie.kogito.jobs.service.api.PayloadData;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import static org.kie.kogito.jobs.service.api.recipient.kafka.KafkaRecipientPayloadData.BINARY;
import static org.kie.kogito.jobs.service.api.recipient.kafka.KafkaRecipientPayloadData.STRING;
import static org.kie.kogito.jobs.service.api.recipient.kafka.KafkaRecipientPayloadData.TYPE;

@Schema(
        discriminatorProperty = TYPE,
        properties = { @SchemaProperty(name = TYPE, type = SchemaType.STRING) },
        requiredProperties = { TYPE },
        discriminatorMapping = {
                @DiscriminatorMapping(value = BINARY, schema = KafkaRecipientBinaryPayloadData.class),
                @DiscriminatorMapping(value = STRING, schema = KafkaRecipientStringPayloadData.class)

        })
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = TYPE)
@JsonSubTypes({
        @JsonSubTypes.Type(name = BINARY, value = KafkaRecipientBinaryPayloadData.class),
        @JsonSubTypes.Type(name = BINARY, value = KafkaRecipientStringPayloadData.class)
})
public abstract class KafkaRecipientPayloadData<T> extends PayloadData<T> {

    static final String TYPE = "type";
    static final String STRING = "string";
    static final String BINARY = "binary";
}

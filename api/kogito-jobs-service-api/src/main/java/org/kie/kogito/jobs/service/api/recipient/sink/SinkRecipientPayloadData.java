package org.kie.kogito.jobs.service.api.recipient.sink;

import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.DiscriminatorMapping;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.media.SchemaProperty;
import org.kie.kogito.jobs.service.api.PayloadData;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import static org.kie.kogito.jobs.service.api.recipient.sink.SinkRecipientPayloadData.BINARY;
import static org.kie.kogito.jobs.service.api.recipient.sink.SinkRecipientPayloadData.JSON;
import static org.kie.kogito.jobs.service.api.recipient.sink.SinkRecipientPayloadData.TYPE;

@Schema(discriminatorProperty = TYPE,
        properties = { @SchemaProperty(name = TYPE, type = SchemaType.STRING) },
        requiredProperties = { TYPE },
        discriminatorMapping = {
                @DiscriminatorMapping(value = BINARY, schema = SinkRecipientBinaryPayloadData.class),
                @DiscriminatorMapping(value = JSON, schema = SinkRecipientJsonPayloadData.class)
        })
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = TYPE)
@JsonSubTypes({
        @JsonSubTypes.Type(name = BINARY, value = SinkRecipientBinaryPayloadData.class),
        @JsonSubTypes.Type(name = JSON, value = SinkRecipientJsonPayloadData.class)

})
public abstract class SinkRecipientPayloadData<T> extends PayloadData<T> {

    static final String TYPE = "type";
    static final String BINARY = "binary";
    static final String JSON = "json";

    protected SinkRecipientPayloadData() {
        // Marshalling constructor.
    }
}

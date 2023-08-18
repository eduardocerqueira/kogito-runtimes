package org.kie.kogito.jobs.service.api;

import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.media.SchemaProperty;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

import static org.kie.kogito.jobs.service.api.Recipient.TYPE_PROPERTY;

@Schema(discriminatorProperty = TYPE_PROPERTY,
        properties = { @SchemaProperty(name = TYPE_PROPERTY, type = SchemaType.STRING) },
        requiredProperties = { TYPE_PROPERTY },
        description = "Generic definition for a Recipient, users must provide instances of subclasses of this schema to create a job.")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = TYPE_PROPERTY)
public abstract class Recipient<T extends PayloadData> implements HasPayload<T> {

    static final String TYPE_PROPERTY = "type";
    public static final String PAYLOAD_PROPERTY = "payload";

    protected Recipient() {
        // Marshalling constructor.
    }
}

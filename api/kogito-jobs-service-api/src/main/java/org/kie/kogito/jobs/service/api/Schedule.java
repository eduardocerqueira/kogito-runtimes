package org.kie.kogito.jobs.service.api;

import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.media.SchemaProperty;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

import static org.kie.kogito.jobs.service.api.Schedule.TYPE_PROPERTY;

@Schema(discriminatorProperty = TYPE_PROPERTY,
        properties = { @SchemaProperty(name = TYPE_PROPERTY, type = SchemaType.STRING) },
        requiredProperties = { TYPE_PROPERTY },
        description = "Generic definition for a Schedule, users must provide instances of subclasses of this schema.")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = TYPE_PROPERTY)
public abstract class Schedule {

    static final String TYPE_PROPERTY = "type";

    protected Schedule() {
        // Marshalling constructor.
    }
}

package org.jbpm.process.core.datatype.impl.coverter;

import java.io.IOException;

import org.jbpm.process.core.datatype.DataType;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class DataTypeSerializer extends JsonSerializer<DataType> {

    @Override
    public void serialize(DataType value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeString(value.getStringType());
    }
}

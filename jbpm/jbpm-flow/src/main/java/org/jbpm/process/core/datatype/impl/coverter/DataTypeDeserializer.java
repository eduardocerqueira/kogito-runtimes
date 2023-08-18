package org.jbpm.process.core.datatype.impl.coverter;

import java.io.IOException;

import org.jbpm.process.core.datatype.DataType;
import org.jbpm.process.core.datatype.DataTypeResolver;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class DataTypeDeserializer extends JsonDeserializer<DataType> {

    @Override
    public DataType deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        return DataTypeResolver.fromType(p.getValueAsString(), Thread.currentThread().getContextClassLoader());
    }
}

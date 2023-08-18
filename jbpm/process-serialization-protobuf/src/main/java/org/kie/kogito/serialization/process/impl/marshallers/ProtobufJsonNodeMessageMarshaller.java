package org.kie.kogito.serialization.process.impl.marshallers;

import org.kie.kogito.jackson.utils.ObjectMapperFactory;
import org.kie.kogito.serialization.process.ObjectMarshallerStrategy;
import org.kie.kogito.serialization.process.ProcessInstanceMarshallerException;
import org.kie.kogito.serialization.process.protobuf.KogitoTypesProtobuf;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.protobuf.Any;
import com.google.protobuf.InvalidProtocolBufferException;

public class ProtobufJsonNodeMessageMarshaller implements ObjectMarshallerStrategy {

    @Override
    public boolean acceptForMarshalling(Object value) {
        return value instanceof JsonNode;
    }

    @Override
    public boolean acceptForUnmarshalling(Any value) {
        return value.is(KogitoTypesProtobuf.JsonNode.class);
    }

    @Override
    public Any marshall(Object unmarshalled) {
        KogitoTypesProtobuf.JsonNode.Builder builder = KogitoTypesProtobuf.JsonNode.newBuilder();
        JsonNode node = (JsonNode) unmarshalled;
        builder.setContent(node.toPrettyString());
        return Any.pack(builder.build());
    }

    @Override
    public Object unmarshall(Any data) {
        try {
            KogitoTypesProtobuf.JsonNode storedValue = data.unpack(KogitoTypesProtobuf.JsonNode.class);
            return ObjectMapperFactory.get().readTree(storedValue.getContent());
        } catch (InvalidProtocolBufferException | JsonProcessingException e1) {
            throw new ProcessInstanceMarshallerException("Error trying to unmarshalling a Json Node value", e1);
        }
    }
}
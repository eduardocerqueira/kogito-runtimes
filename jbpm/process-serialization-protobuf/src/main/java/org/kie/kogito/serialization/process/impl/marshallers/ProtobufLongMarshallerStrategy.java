package org.kie.kogito.serialization.process.impl.marshallers;

import org.kie.kogito.serialization.process.ObjectMarshallerStrategy;
import org.kie.kogito.serialization.process.ProcessInstanceMarshallerException;

import com.google.protobuf.Any;
import com.google.protobuf.Int64Value;
import com.google.protobuf.InvalidProtocolBufferException;

public class ProtobufLongMarshallerStrategy implements ObjectMarshallerStrategy {

    @Override
    public boolean acceptForMarshalling(Object value) {
        return Long.class.equals(value.getClass());
    }

    @Override
    public boolean acceptForUnmarshalling(Any value) {
        return value.is(Int64Value.class);
    }

    @Override
    public Any marshall(Object unmarshalled) {
        return Any.pack(Int64Value.of((Long) unmarshalled));
    }

    @Override
    public Object unmarshall(Any data) {
        try {
            Int64Value storedValue = data.unpack(Int64Value.class);
            return storedValue.getValue();
        } catch (InvalidProtocolBufferException e1) {
            throw new ProcessInstanceMarshallerException("Error trying to unmarshalling a long value", e1);
        }
    }
}

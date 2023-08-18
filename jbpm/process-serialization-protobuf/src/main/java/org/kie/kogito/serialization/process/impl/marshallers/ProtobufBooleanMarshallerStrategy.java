package org.kie.kogito.serialization.process.impl.marshallers;

import org.kie.kogito.serialization.process.ObjectMarshallerStrategy;
import org.kie.kogito.serialization.process.ProcessInstanceMarshallerException;

import com.google.protobuf.Any;
import com.google.protobuf.BoolValue;
import com.google.protobuf.InvalidProtocolBufferException;

public class ProtobufBooleanMarshallerStrategy implements ObjectMarshallerStrategy {

    @Override
    public boolean acceptForMarshalling(Object value) {
        return Boolean.class.equals(value.getClass());
    }

    @Override
    public boolean acceptForUnmarshalling(Any value) {
        return value.is(BoolValue.class);
    }

    @Override
    public Any marshall(Object unmarshalled) {
        return Any.pack(BoolValue.of((Boolean) unmarshalled));
    }

    @Override
    public Object unmarshall(Any data) {
        try {
            BoolValue storedValue = data.unpack(BoolValue.class);
            return storedValue.getValue();
        } catch (InvalidProtocolBufferException e1) {
            throw new ProcessInstanceMarshallerException("Error trying to unmarshalling a boolean value", e1);
        }
    }

}

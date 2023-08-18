package org.kie.kogito.serialization.process.impl.marshallers;

import org.kie.kogito.serialization.process.ObjectMarshallerStrategy;
import org.kie.kogito.serialization.process.ProcessInstanceMarshallerException;

import com.google.protobuf.Any;
import com.google.protobuf.Int32Value;
import com.google.protobuf.InvalidProtocolBufferException;

public class ProtobufIntegerMarshallerStrategy implements ObjectMarshallerStrategy {

    @Override
    public boolean acceptForMarshalling(Object value) {
        return Integer.class.equals(value.getClass());
    }

    @Override
    public boolean acceptForUnmarshalling(Any value) {
        return value.is(Int32Value.class);
    }

    @Override
    public Any marshall(Object unmarshalled) {
        return Any.pack(Int32Value.of((Integer) unmarshalled));
    }

    @Override
    public Object unmarshall(Any data) {
        try {
            Int32Value storedValue = data.unpack(Int32Value.class);
            return storedValue.getValue();
        } catch (InvalidProtocolBufferException e1) {
            throw new ProcessInstanceMarshallerException("Error trying to unmarshalling a int value", e1);
        }
    }
}

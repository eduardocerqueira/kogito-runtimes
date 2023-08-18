package org.kie.kogito.serialization.process.impl.marshallers;

import org.kie.kogito.serialization.process.ObjectMarshallerStrategy;
import org.kie.kogito.serialization.process.ProcessInstanceMarshallerException;

import com.google.protobuf.Any;
import com.google.protobuf.DoubleValue;
import com.google.protobuf.InvalidProtocolBufferException;

public class ProtobufDoubleMarshallerStrategy implements ObjectMarshallerStrategy {

    @Override
    public boolean acceptForMarshalling(Object value) {
        return Double.class.equals(value.getClass());
    }

    @Override
    public boolean acceptForUnmarshalling(Any value) {
        return value.is(DoubleValue.class);
    }

    @Override
    public Any marshall(Object unmarshalled) {
        return Any.pack(DoubleValue.of((Double) unmarshalled));
    }

    @Override
    public Object unmarshall(Any data) {
        try {
            DoubleValue storedValue = data.unpack(DoubleValue.class);
            return storedValue.getValue();
        } catch (InvalidProtocolBufferException e1) {
            throw new ProcessInstanceMarshallerException("Error trying to unmarshalling a double value", e1);
        }
    }

}

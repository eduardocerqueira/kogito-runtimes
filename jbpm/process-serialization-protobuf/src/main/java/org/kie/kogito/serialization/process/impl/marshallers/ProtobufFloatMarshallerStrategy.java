package org.kie.kogito.serialization.process.impl.marshallers;

import org.kie.kogito.serialization.process.ObjectMarshallerStrategy;
import org.kie.kogito.serialization.process.ProcessInstanceMarshallerException;

import com.google.protobuf.Any;
import com.google.protobuf.FloatValue;
import com.google.protobuf.InvalidProtocolBufferException;

public class ProtobufFloatMarshallerStrategy implements ObjectMarshallerStrategy {

    @Override
    public boolean acceptForMarshalling(Object value) {
        return Float.class.equals(value.getClass());
    }

    @Override
    public boolean acceptForUnmarshalling(Any value) {
        return value.is(FloatValue.class);
    }

    @Override
    public Any marshall(Object unmarshalled) {
        return Any.pack(FloatValue.of((Float) unmarshalled));
    }

    @Override
    public Object unmarshall(Any data) {
        try {
            FloatValue storedValue = data.unpack(FloatValue.class);
            return storedValue.getValue();
        } catch (InvalidProtocolBufferException e1) {
            throw new ProcessInstanceMarshallerException("Error trying to unmarshalling a float value", e1);
        }
    }

}

package org.kie.kogito.serialization.process.impl.marshallers;

import java.time.Instant;
import java.util.Date;

import org.kie.kogito.serialization.process.ObjectMarshallerStrategy;
import org.kie.kogito.serialization.process.ProcessInstanceMarshallerException;

import com.google.protobuf.Any;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Timestamp;
import com.google.protobuf.util.Timestamps;

public class ProtobufDateMarshallerStrategy implements ObjectMarshallerStrategy {

    @Override
    public boolean acceptForMarshalling(Object value) {
        return Date.class.equals(value.getClass());
    }

    @Override
    public boolean acceptForUnmarshalling(Any value) {
        return value.is(Timestamp.class);
    }

    @Override
    public Any marshall(Object unmarshalled) {
        Timestamp timestamp = Timestamps.fromMillis(((Date) unmarshalled).getTime());
        return Any.pack(timestamp);
    }

    @Override
    public Object unmarshall(Any data) {
        try {
            Timestamp storedValue = data.unpack(Timestamp.class);
            return new Date(Instant.ofEpochSecond(storedValue.getSeconds(), storedValue.getNanos()).toEpochMilli());
        } catch (InvalidProtocolBufferException e1) {
            throw new ProcessInstanceMarshallerException("Error trying to unmarshalling a date value", e1);
        }
    }

}

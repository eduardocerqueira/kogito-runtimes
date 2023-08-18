package org.kie.kogito.serialization.process;

import com.google.protobuf.Any;

public interface ObjectMarshallerStrategy extends Comparable<ObjectMarshallerStrategy> {

    Integer DEFAULT_ORDER = 10;

    default Integer order() {
        return DEFAULT_ORDER;
    }

    @Override
    default int compareTo(ObjectMarshallerStrategy that) {
        return that.order().compareTo(this.order());
    }

    boolean acceptForMarshalling(Object value);

    Any marshall(Object unmarshalled);

    boolean acceptForUnmarshalling(Any value);

    Object unmarshall(Any marshalled);

}

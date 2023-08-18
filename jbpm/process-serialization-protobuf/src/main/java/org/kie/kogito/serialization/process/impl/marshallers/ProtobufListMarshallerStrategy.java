package org.kie.kogito.serialization.process.impl.marshallers;

import java.util.List;

public class ProtobufListMarshallerStrategy extends ProtobufObjectMarshallerStrategy {

    @Override
    public Integer order() {
        return DEFAULT_ORDER;
    }

    @Override
    public boolean acceptForMarshalling(Object value) {
        return List.class.isInstance(value);
    }
}

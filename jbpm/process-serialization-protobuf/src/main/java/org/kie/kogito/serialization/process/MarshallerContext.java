package org.kie.kogito.serialization.process;

import com.google.protobuf.Any;

public interface MarshallerContext {

    ObjectMarshallerStrategy findObjectMarshallerStrategyFor(Object value);

    ObjectMarshallerStrategy findObjectUnmarshallerStrategyFor(Any value);

    <T> T get(MarshallerContextName<T> key);

    <T> void set(MarshallerContextName<T> key, T value);

}

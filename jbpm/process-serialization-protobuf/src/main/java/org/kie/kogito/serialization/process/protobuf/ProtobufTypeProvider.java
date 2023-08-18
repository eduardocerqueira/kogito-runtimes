package org.kie.kogito.serialization.process.protobuf;

import java.util.Collection;

import com.google.protobuf.Descriptors;

public interface ProtobufTypeProvider {

    Collection<Descriptors.Descriptor> descriptors();

}

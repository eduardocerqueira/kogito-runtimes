package org.kie.kogito.serialization.process.protobuf;

import java.util.ArrayList;
import java.util.Collection;
import java.util.ServiceLoader;

import com.google.protobuf.Descriptors.Descriptor;
import com.google.protobuf.Empty;
import com.google.protobuf.Timestamp;
import com.google.protobuf.TypeRegistry;
import com.google.protobuf.WrappersProto;

public final class ProtobufTypeRegistryFactory {

    private static ProtobufTypeRegistryFactory INSTANCE = new ProtobufTypeRegistryFactory();

    private Collection<Descriptor> descriptors;

    private ProtobufTypeRegistryFactory() {
        descriptors = new ArrayList<>();
        ServiceLoader<ProtobufTypeProvider> providers = ServiceLoader.load(ProtobufTypeProvider.class);
        for(ProtobufTypeProvider provider : providers) {
            descriptors.addAll(provider.descriptors());
        }
    }

    public static ProtobufTypeRegistryFactory protobufTypeRegistryFactoryInstance() {
        return INSTANCE;
    }

    public TypeRegistry create() {
        TypeRegistry.Builder builder = TypeRegistry.newBuilder();

        builder.add(KogitoTypesProtobuf.getDescriptor().getMessageTypes())
                .add(KogitoProcessInstanceProtobuf.getDescriptor().getMessageTypes())
                .add(KogitoNodeInstanceContentsProtobuf.getDescriptor().getMessageTypes())
                .add(KogitoWorkItemsProtobuf.getDescriptor().getMessageTypes())
                .add(WrappersProto.getDescriptor().getMessageTypes())
                .add(Timestamp.getDescriptor())
                .add(Empty.getDescriptor());

        // we add custom providers / user types
        builder.add(descriptors);
        return builder.build();
    }
}

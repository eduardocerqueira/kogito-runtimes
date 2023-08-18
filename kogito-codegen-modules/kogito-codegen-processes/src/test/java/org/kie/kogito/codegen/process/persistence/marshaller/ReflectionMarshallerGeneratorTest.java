package org.kie.kogito.codegen.process.persistence.marshaller;

import java.util.Collection;

import org.kie.kogito.codegen.api.context.KogitoBuildContext;
import org.kie.kogito.codegen.process.persistence.proto.ProtoGenerator;
import org.kie.kogito.codegen.process.persistence.proto.ReflectionProtoGenerator;

public class ReflectionMarshallerGeneratorTest extends AbstractMarshallerGeneratorTest<Class<?>> {

    @Override
    protected ProtoGenerator.Builder protoGeneratorBuilder() {
        return ReflectionProtoGenerator.builder();
    }

    @Override
    protected MarshallerGenerator generator(KogitoBuildContext context, Collection<Class<?>> rawDataClasses) {
        return new ReflectionMarshallerGenerator(context, rawDataClasses);
    }

    @Override
    protected Class<?> convertToType(Class<?> clazz) {
        return clazz;
    }
}

package org.kie.kogito.codegen.process.persistence.proto;

/**
 * This class is intended to cover only ReflectionProtoGeneratorTest specific tests (if any)
 *
 * NOTE: Add all tests to AbstractProtoGeneratorTest class to test both JandexProtoGenerator and ReflectionProtoGenerator
 */
class ReflectionProtoGeneratorTest extends AbstractProtoGeneratorTest<Class<?>> {

    @Override
    protected ProtoGenerator.Builder<Class<?>, ReflectionProtoGenerator> protoGeneratorBuilder() {
        return ReflectionProtoGenerator.builder();
    }

    @Override
    protected Class<?> convertToType(Class<?> clazz) {
        return clazz;
    }

}

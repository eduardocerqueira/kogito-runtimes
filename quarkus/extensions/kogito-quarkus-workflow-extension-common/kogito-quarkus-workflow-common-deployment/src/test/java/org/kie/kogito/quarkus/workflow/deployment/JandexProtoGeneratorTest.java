package org.kie.kogito.quarkus.workflow.deployment;

import org.jboss.jandex.ClassInfo;
import org.jboss.jandex.Index;
import org.junit.jupiter.api.BeforeAll;
import org.kie.kogito.codegen.process.persistence.proto.AbstractProtoGeneratorTest;
import org.kie.kogito.codegen.process.persistence.proto.ProtoGenerator;

/**
 * This class is intended to cover only JandexProtoGenerator specific tests (if any)
 *
 * NOTE: Add all tests to AbstractProtoGeneratorTest class to test both JandexProtoGenerator and ReflectionProtoGenerator
 */
class JandexProtoGeneratorTest extends AbstractProtoGeneratorTest<ClassInfo> {

    protected static Index indexWithAllClass;

    @BeforeAll
    protected static void indexOfTestClasses() {
        indexWithAllClass = JandexTestUtils.createTestIndex();
    }

    @Override
    protected ProtoGenerator.Builder<ClassInfo, JandexProtoGenerator> protoGeneratorBuilder() {
        return JandexProtoGenerator.builder(indexWithAllClass);
    }

    @Override
    protected ClassInfo convertToType(Class<?> clazz) {
        return JandexTestUtils.findClassInfo(indexWithAllClass, clazz);
    }

}

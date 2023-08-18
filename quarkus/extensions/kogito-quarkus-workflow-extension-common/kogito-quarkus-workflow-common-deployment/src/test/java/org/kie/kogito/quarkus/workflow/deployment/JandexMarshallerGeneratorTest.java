package org.kie.kogito.quarkus.workflow.deployment;

import java.util.Collection;

import org.jboss.jandex.ClassInfo;
import org.jboss.jandex.Index;
import org.junit.jupiter.api.BeforeAll;
import org.kie.kogito.codegen.api.context.KogitoBuildContext;
import org.kie.kogito.codegen.process.persistence.marshaller.AbstractMarshallerGeneratorTest;
import org.kie.kogito.codegen.process.persistence.marshaller.MarshallerGenerator;
import org.kie.kogito.codegen.process.persistence.proto.ProtoGenerator;

public class JandexMarshallerGeneratorTest extends AbstractMarshallerGeneratorTest<ClassInfo> {

    protected static Index indexWithAllClass;

    @BeforeAll
    protected static void indexOfTestClasses() {
        indexWithAllClass = JandexTestUtils.createTestIndex();
    }

    @Override
    protected MarshallerGenerator generator(KogitoBuildContext context, Collection<ClassInfo> rawDataClasses) {
        return new JandexMarshallerGenerator(context, rawDataClasses);
    }

    @Override
    protected ProtoGenerator.Builder<ClassInfo, JandexProtoGenerator> protoGeneratorBuilder() {
        return JandexProtoGenerator.builder(indexWithAllClass);
    }

    @Override
    protected ClassInfo convertToType(Class clazz) {
        return JandexTestUtils.findClassInfo(indexWithAllClass, clazz);
    }
}

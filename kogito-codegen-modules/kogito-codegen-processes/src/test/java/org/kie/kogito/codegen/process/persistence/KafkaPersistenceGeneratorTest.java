package org.kie.kogito.codegen.process.persistence;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.drools.codegen.common.GeneratedFile;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.kie.kogito.codegen.api.context.KogitoBuildContext;
import org.kie.kogito.codegen.data.GeneratedPOJO;
import org.kie.kogito.codegen.process.persistence.marshaller.ReflectionMarshallerGenerator;
import org.kie.kogito.codegen.process.persistence.proto.ReflectionProtoGenerator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.kie.kogito.codegen.process.persistence.PersistenceGenerator.KAFKA_PERSISTENCE_TYPE;
import static org.kie.kogito.codegen.process.persistence.PersistenceGenerator.KOGITO_PERSISTENCE_TYPE;
import static org.kie.kogito.codegen.process.persistence.PersistenceGenerator.hasDataIndexProto;
import static org.kie.kogito.codegen.process.persistence.PersistenceGenerator.hasProtoMarshaller;

class KafkaPersistenceGeneratorTest extends AbstractPersistenceGeneratorTest {

    @ParameterizedTest
    @MethodSource("persistenceTestContexts")
    void test(KogitoBuildContext context) {
        context.setApplicationProperty(KOGITO_PERSISTENCE_TYPE, persistenceType());

        ReflectionProtoGenerator protoGenerator = ReflectionProtoGenerator.builder().build(Collections.singleton(GeneratedPOJO.class));
        PersistenceGenerator persistenceGenerator = new PersistenceGenerator(
                context,
                protoGenerator,
                new ReflectionMarshallerGenerator(context));
        Collection<GeneratedFile> generatedFiles = persistenceGenerator.generate();

        if (context.hasDI()) {
            if (hasProtoMarshaller(context)) {
                List<GeneratedFile> marshallerFiles = generatedFiles.stream().filter(gf -> gf.relativePath().endsWith("MessageMarshaller.java")).collect(Collectors.toList());

                String expectedMarshaller = "PersonMessageMarshaller";
                assertThat(marshallerFiles).hasSize(1);
                assertThat(marshallerFiles.get(0).relativePath()).endsWith(expectedMarshaller + ".java");
            }
        }

        int marshallerFiles = hasProtoMarshaller(context) ? 14 : 0;
        int dataIndexFiles = hasDataIndexProto(context) ? 2 : 0;
        int expectedNumberOfFiles = marshallerFiles + dataIndexFiles;
        assertThat(generatedFiles).hasSize(expectedNumberOfFiles);
    }

    @Override
    protected String persistenceType() {
        return KAFKA_PERSISTENCE_TYPE;
    }
}

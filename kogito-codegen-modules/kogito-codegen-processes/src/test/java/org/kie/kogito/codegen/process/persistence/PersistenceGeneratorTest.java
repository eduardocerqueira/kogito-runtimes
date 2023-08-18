package org.kie.kogito.codegen.process.persistence;

import java.util.Collection;

import org.drools.codegen.common.GeneratedFile;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.kie.kogito.codegen.api.AddonsConfig;
import org.kie.kogito.codegen.api.context.KogitoBuildContext;

import static org.assertj.core.api.Assertions.assertThat;

class PersistenceGeneratorTest {

    @ParameterizedTest
    @MethodSource("org.kie.kogito.codegen.api.utils.KogitoContextTestUtils#contextBuilders")
    public void isEmpty(KogitoBuildContext.Builder contextBuilder) {
        KogitoBuildContext noPersistenceContext = contextBuilder
                .withAddonsConfig(AddonsConfig.builder()
                        .withPersistence(false)
                        .build())
                .build();

        PersistenceGenerator emptyCodeGenerator = new PersistenceGenerator(noPersistenceContext, null, null);

        assertThat(emptyCodeGenerator.isEmpty()).isTrue();
        assertThat(emptyCodeGenerator.isEnabled()).isFalse();

        Collection<GeneratedFile> emptyGeneratedFiles = emptyCodeGenerator.generate();
        assertThat(emptyGeneratedFiles).isEmpty();

        KogitoBuildContext persistenceContext = contextBuilder
                .withAddonsConfig(AddonsConfig.builder()
                        .withPersistence(true)
                        .build())
                .build();
        PersistenceGenerator codeGenerator = new PersistenceGenerator(persistenceContext, null, null);

        assertThat(codeGenerator.isEmpty()).isFalse();
        assertThat(codeGenerator.isEnabled()).isTrue();
    }

}

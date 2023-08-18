package org.kie.kogito.codegen.sample.generator;

import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.kie.kogito.codegen.api.context.KogitoBuildContext;

import com.github.javaparser.ast.body.MethodDeclaration;

import static org.assertj.core.api.Assertions.assertThat;

class SampleContainerGeneratorTest {

    @ParameterizedTest
    @MethodSource("org.kie.kogito.codegen.api.utils.KogitoContextTestUtils#contextBuilders")
    void compilationUnit(KogitoBuildContext.Builder contextBuilder) {
        KogitoBuildContext context = contextBuilder.build();
        Collection<SampleResource> resources = Arrays.asList(
                new SampleResource("sampleFile1", "content1"),
                new SampleResource("sampleFile2", "content2"));

        SampleContainerGenerator sampleContainerGenerator = new SampleContainerGenerator(context, resources);
        Optional<String> optionalLoadContent = sampleContainerGenerator.compilationUnit()
                .findFirst(MethodDeclaration.class, md -> "loadContent".equals(md.getName().asString()))
                .flatMap(MethodDeclaration::getBody)
                .map(Objects::toString);

        assertThat(optionalLoadContent).isNotEmpty();

        String loadContent = optionalLoadContent.get();

        resources.forEach(resource -> assertThat(loadContent)
                .contains(resource.getName())
                .contains(resource.getContent()));
    }
}

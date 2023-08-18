package org.kie.kogito.codegen.sample.generator.config;

import java.util.Optional;

import org.drools.codegen.common.GeneratedFile;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.kie.kogito.codegen.api.context.KogitoBuildContext;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;

import static org.assertj.core.api.Assertions.assertThat;

class SampleConfigGeneratorTest {

    @ParameterizedTest
    @MethodSource("org.kie.kogito.codegen.api.utils.KogitoContextTestUtils#contextBuilders")
    void generate(KogitoBuildContext.Builder contextBuilder) {
        KogitoBuildContext context = contextBuilder.build();

        SampleConfigGenerator sampleConfigGenerator = new SampleConfigGenerator(context);

        GeneratedFile generate = sampleConfigGenerator.generate();
        CompilationUnit compilationUnit = StaticJavaParser.parse(new String(generate.contents()));

        Optional<ClassOrInterfaceDeclaration> optionalClassDeclaration = compilationUnit.findFirst(ClassOrInterfaceDeclaration.class);

        assertThat(optionalClassDeclaration).isNotEmpty();

        ClassOrInterfaceDeclaration classDeclaration = optionalClassDeclaration.get();

        if (context.hasDI()) {
            assertThat(classDeclaration.getAnnotations()).isNotEmpty();
        } else {
            assertThat(classDeclaration.getAnnotations()).isEmpty();
        }
    }
}

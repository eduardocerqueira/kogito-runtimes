package org.kie.kogito.codegen.sample.generator.config;

import org.drools.codegen.common.GeneratedFile;
import org.kie.kogito.codegen.api.ConfigGenerator;
import org.kie.kogito.codegen.api.context.KogitoBuildContext;
import org.kie.kogito.codegen.api.template.TemplatedGenerator;

import com.github.javaparser.ast.CompilationUnit;

public class SampleConfigGenerator implements ConfigGenerator {

    private final TemplatedGenerator generator;

    public SampleConfigGenerator(KogitoBuildContext context) {
        this.generator = TemplatedGenerator.builder()
                .withTemplateBasePath("class-templates/config")
                .build(context, "SampleConfig");
    }

    @Override
    public String configClassName() {
        return "SampleConfig";
    }

    @Override
    public GeneratedFile generate() {
        CompilationUnit compilationUnit = generator.compilationUnitOrThrow();
        return new GeneratedFile(APPLICATION_CONFIG_TYPE,
                generator.generatedFilePath(),
                compilationUnit.toString());
    }
}

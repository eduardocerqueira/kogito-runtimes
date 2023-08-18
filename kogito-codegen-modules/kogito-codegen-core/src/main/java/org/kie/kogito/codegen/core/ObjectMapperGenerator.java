package org.kie.kogito.codegen.core;

import org.drools.codegen.common.GeneratedFile;
import org.kie.kogito.codegen.api.Generator;
import org.kie.kogito.codegen.api.context.KogitoBuildContext;
import org.kie.kogito.codegen.api.template.TemplatedGenerator;

public class ObjectMapperGenerator {

    private ObjectMapperGenerator() {
    }

    public static GeneratedFile generate(KogitoBuildContext context) {
        TemplatedGenerator generator = TemplatedGenerator.builder()
                .withTemplateBasePath("class-templates/config")
                .build(context, "GlobalObjectMapper");

        return new GeneratedFile(Generator.REST_TYPE,
                generator.generatedFilePath(),
                generator.compilationUnitOrThrow().toString());
    }
}

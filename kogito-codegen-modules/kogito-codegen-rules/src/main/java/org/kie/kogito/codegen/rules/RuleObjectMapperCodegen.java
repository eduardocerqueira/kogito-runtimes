package org.kie.kogito.codegen.rules;

import org.drools.codegen.common.GeneratedFile;
import org.kie.kogito.codegen.api.Generator;
import org.kie.kogito.codegen.api.context.KogitoBuildContext;
import org.kie.kogito.codegen.api.template.TemplatedGenerator;

public class RuleObjectMapperCodegen {

    private final KogitoBuildContext context;

    public RuleObjectMapperCodegen(KogitoBuildContext context) {
        this.context = context;
    }

    GeneratedFile generate() {
        TemplatedGenerator generator = TemplatedGenerator.builder()
                .withTemplateBasePath(RuleCodegen.TEMPLATE_RULE_FOLDER)
                .build(context, "KogitoObjectMapper");

        return new GeneratedFile(Generator.REST_TYPE,
                generator.generatedFilePath(),
                generator.compilationUnitOrThrow().toString());
    }
}

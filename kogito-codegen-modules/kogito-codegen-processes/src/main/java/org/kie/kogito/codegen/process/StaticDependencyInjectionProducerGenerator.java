package org.kie.kogito.codegen.process;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.kie.kogito.codegen.api.context.KogitoBuildContext;
import org.kie.kogito.codegen.api.context.impl.JavaKogitoBuildContext;
import org.kie.kogito.codegen.api.template.TemplatedGenerator;

public class StaticDependencyInjectionProducerGenerator {

    private final KogitoBuildContext context;
    //All available Producer templates for dependency injection
    private static final List<String> producerTemplates = Arrays.asList("ProcessServiceProducer");

    private StaticDependencyInjectionProducerGenerator(
            KogitoBuildContext context) {
        this.context = context;
    }

    public static StaticDependencyInjectionProducerGenerator of(KogitoBuildContext context) {
        return new StaticDependencyInjectionProducerGenerator(context);
    }

    /**
     * Key is the FilePath, Value is the content
     *
     * @return Map with the generated resources
     */
    public Map<String, String> generate() {
        if (!context.hasDI()) {
            return Collections.emptyMap();
        }
        return producerTemplates.stream()
                .map(this::buildProducerTemplatedGenerator)
                .collect(Collectors.toMap(TemplatedGenerator::generatedFilePath,
                        generator -> generator.compilationUnitOrThrow().toString()));
    }

    private TemplatedGenerator buildProducerTemplatedGenerator(String template) {
        return TemplatedGenerator.builder()
                .withTemplateBasePath("/class-templates/producer/")
                .withFallbackContext(JavaKogitoBuildContext.CONTEXT_NAME)
                .withTargetTypeName(template)
                .build(context, template);
    }
}

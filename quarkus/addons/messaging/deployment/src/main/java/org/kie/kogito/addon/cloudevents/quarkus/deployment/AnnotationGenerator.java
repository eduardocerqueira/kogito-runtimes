package org.kie.kogito.addon.cloudevents.quarkus.deployment;

import org.kie.kogito.codegen.api.context.KogitoBuildContext;
import org.kie.kogito.codegen.api.template.InvalidTemplateException;
import org.kie.kogito.codegen.api.template.TemplatedGenerator;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.AnnotationDeclaration;

public class AnnotationGenerator implements ClassGenerator {

    private static final String TEMPLATE_NAME = "ChannelQualifier";

    private TemplatedGenerator template;
    private CompilationUnit generator;

    public AnnotationGenerator(KogitoBuildContext context, String className) {
        template = TemplatedGenerator.builder()
                .withTargetTypeName(className)
                .build(context, TEMPLATE_NAME);
        generator = template.compilationUnitOrThrow("Cannot generate " + TEMPLATE_NAME);
        AnnotationDeclaration clazz = generator.findFirst(AnnotationDeclaration.class).orElseThrow(() -> new InvalidTemplateException(template, "Cannot find class declaration"));
        clazz.setName(className);
    }

    @Override
    public String getCode() {
        return generator.toString();
    }

    @Override
    public String getPath() {
        return template.generatedFilePath();
    }
}

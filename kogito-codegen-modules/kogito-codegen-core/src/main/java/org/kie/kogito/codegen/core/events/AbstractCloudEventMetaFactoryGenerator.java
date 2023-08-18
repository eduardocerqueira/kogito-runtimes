package org.kie.kogito.codegen.core.events;

import java.util.List;

import org.kie.kogito.codegen.api.context.KogitoBuildContext;
import org.kie.kogito.codegen.api.context.impl.JavaKogitoBuildContext;
import org.kie.kogito.codegen.api.template.TemplatedGenerator;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;

public abstract class AbstractCloudEventMetaFactoryGenerator extends AbstractEventResourceGenerator {

    protected final KogitoBuildContext context;

    protected AbstractCloudEventMetaFactoryGenerator(TemplatedGenerator generator, KogitoBuildContext context) {
        super(generator);
        this.context = context;
    }

    public static TemplatedGenerator buildTemplatedGenerator(KogitoBuildContext context, String className) {
        return TemplatedGenerator.builder()
                .withTemplateBasePath(TEMPLATE_EVENT_FOLDER)
                .withFallbackContext(JavaKogitoBuildContext.CONTEXT_NAME)
                .build(context, className);
    }

    public static String getBuilderMethodName(ClassOrInterfaceDeclaration classDefinition, String templatedBuildMethodName, String methodNameValue) {
        String baseMethodName = templatedBuildMethodName.replace("$methodName$", methodNameValue);
        List<MethodDeclaration> methods = classDefinition.findAll(MethodDeclaration.class);
        int counter = 0;
        while (true) {
            String expectedMethodName = counter == 0
                    ? baseMethodName
                    : String.format("%s_%d", baseMethodName, counter);
            if (methods.stream().anyMatch(m -> m.getNameAsString().equals(expectedMethodName))) {
                counter++;
            } else {
                return expectedMethodName;
            }
        }
    }

    public static String toValidJavaIdentifier(String input) {
        StringBuilder sb = new StringBuilder(input.length());
        for (char c : input.toCharArray()) {
            if (c == '_') {
                sb.append("__");
            } else if (!Character.isJavaIdentifierPart(c)) {
                sb.append("_").append(Integer.valueOf(c));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    protected abstract CloudEventMetaBuilder<?, ?> getCloudEventMetaBuilder();
}

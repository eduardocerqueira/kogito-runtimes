package org.kie.kogito.quarkus.serverless.workflow;

import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.jboss.jandex.AnnotationInstance;
import org.jboss.jandex.DotName;
import org.jboss.jandex.IndexView;
import org.jboss.jandex.Type;
import org.kie.kogito.codegen.api.context.KogitoBuildContext;

import static com.github.javaparser.StaticJavaParser.parseClassOrInterfaceType;
import static com.github.javaparser.StaticJavaParser.parseType;

public abstract class ClassAnnotatedWorkflowHandlerGenerator implements WorkflowHandlerGenerator {

    @Override
    public Collection<WorkflowHandlerGeneratedFile> generateHandlerClasses(KogitoBuildContext context, IndexView index) {
        return index.getAnnotations(DotName.createSimple(getAnnotation().getCanonicalName())).stream().flatMap(a -> generateHandler(context, a)).collect(Collectors.toList());
    }

    protected abstract Class<? extends Annotation> getAnnotation();

    protected abstract Stream<WorkflowHandlerGeneratedFile> generateHandler(KogitoBuildContext context, AnnotationInstance a);

    protected final com.github.javaparser.ast.type.Type fromClass(Type param) {
        switch (param.kind()) {
            case CLASS:
                return parseClassOrInterfaceType(param.asClassType().name().toString());
            case PRIMITIVE:
                return parseType(param.asPrimitiveType().name().toString());
            default:
                throw new UnsupportedOperationException("Kind " + param.kind() + " is not supported");
        }
    }
}

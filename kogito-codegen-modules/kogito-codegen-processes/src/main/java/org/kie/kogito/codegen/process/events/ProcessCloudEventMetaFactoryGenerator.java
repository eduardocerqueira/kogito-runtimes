package org.kie.kogito.codegen.process.events;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.kie.kogito.codegen.api.context.KogitoBuildContext;
import org.kie.kogito.codegen.api.template.InvalidTemplateException;
import org.kie.kogito.codegen.core.CodegenUtils;
import org.kie.kogito.codegen.core.events.AbstractCloudEventMetaFactoryGenerator;
import org.kie.kogito.codegen.process.ProcessExecutableModelGenerator;
import org.kie.kogito.event.EventKind;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.FieldAccessExpr;
import com.github.javaparser.ast.expr.NameExpr;
import com.github.javaparser.ast.expr.ObjectCreationExpr;
import com.github.javaparser.ast.expr.SimpleName;
import com.github.javaparser.ast.expr.StringLiteralExpr;

public class ProcessCloudEventMetaFactoryGenerator extends AbstractCloudEventMetaFactoryGenerator {

    static final String CLASS_NAME = "ProcessCloudEventMetaFactory";

    private final List<ProcessExecutableModelGenerator> generators;

    public ProcessCloudEventMetaFactoryGenerator(KogitoBuildContext context, List<ProcessExecutableModelGenerator> generators) {
        super(buildTemplatedGenerator(context, CLASS_NAME), context);
        this.generators = generators;
    }

    @Override
    protected ProcessCloudEventMetaBuilder getCloudEventMetaBuilder() {
        return new ProcessCloudEventMetaBuilder();
    }

    public String generate() {
        CompilationUnit compilationUnit = generator.compilationUnitOrThrow("Cannot generate CloudEventMetaFactory");

        ClassOrInterfaceDeclaration classDefinition = compilationUnit.findFirst(ClassOrInterfaceDeclaration.class)
                .orElseThrow(() -> new InvalidTemplateException(generator, "Compilation unit doesn't contain a class or interface declaration!"));

        MethodDeclaration templatedBuildMethod = classDefinition
                .findFirst(MethodDeclaration.class, x -> x.getName().toString().startsWith("buildCloudEventMeta_"))
                .orElseThrow(() -> new InvalidTemplateException(generator, "Impossible to find expected buildCloudEventMeta_ method"));

        Set<ProcessCloudEventMeta> processCloudEventMetaList = this.getCloudEventMetaBuilder().build(this.generators);

        processCloudEventMetaList.forEach(processCloudEventMeta -> {
            MethodDeclaration builderMethod = templatedBuildMethod.clone();

            String methodNameValue = String.format("%s_%s", processCloudEventMeta.getKind().name(), toValidJavaIdentifier(processCloudEventMeta.triggerName));
            String builderMethodName = getBuilderMethodName(classDefinition, templatedBuildMethod.getNameAsString(), methodNameValue);
            builderMethod.setName(builderMethodName);

            Map<String, Expression> expressions = new HashMap<>();
            expressions.put("$type$", new StringLiteralExpr(processCloudEventMeta.getType()));
            expressions.put("$source$", new StringLiteralExpr(processCloudEventMeta.getSource()));
            expressions.put("$kind$", new FieldAccessExpr(new NameExpr(new SimpleName(EventKind.class.getName())), processCloudEventMeta.getKind().name()));

            ObjectCreationExpr objectCreationExpr = builderMethod.findAll(ObjectCreationExpr.class).get(0);
            CodegenUtils.interpolateArguments(objectCreationExpr, expressions);

            if (context.hasDI()) {
                context.getDependencyInjectionAnnotator().withFactoryMethod(builderMethod);
            }

            classDefinition.addMember(builderMethod);
        });

        templatedBuildMethod.remove();

        if (context.hasDI()) {
            context.getDependencyInjectionAnnotator().withFactoryClass(classDefinition);
        }

        return compilationUnit.toString();
    }
}

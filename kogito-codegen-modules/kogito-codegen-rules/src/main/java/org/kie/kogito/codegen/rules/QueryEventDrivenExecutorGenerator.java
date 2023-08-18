package org.kie.kogito.codegen.rules;

import org.drools.codegen.common.GeneratedFile;
import org.drools.codegen.common.GeneratedFileType;
import org.kie.kogito.codegen.api.template.InvalidTemplateException;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.expr.MethodReferenceExpr;
import com.github.javaparser.ast.expr.NameExpr;
import com.github.javaparser.ast.expr.StringLiteralExpr;
import com.github.javaparser.ast.type.ClassOrInterfaceType;

public class QueryEventDrivenExecutorGenerator extends AbstractQueryEntrypointGenerator {

    private final String dataType;

    public QueryEventDrivenExecutorGenerator(QueryGenerator queryGenerator) {
        super(queryGenerator, "EventDrivenExecutor", "EventDrivenExecutor");
        this.dataType = ruleUnit.getCanonicalName() + (context.hasDI() ? "" : "DTO");
    }

    @Override
    public GeneratedFile generate() {
        CompilationUnit cu = generator.compilationUnitOrThrow("Could not create CompilationUnit");

        ClassOrInterfaceDeclaration classDecl = cu.findFirst(ClassOrInterfaceDeclaration.class)
                .orElseThrow(() -> new InvalidTemplateException(generator, "Cannot find class declaration"));

        classDecl.setName(targetClassName);
        classDecl.findAll(ClassOrInterfaceType.class).forEach(this::interpolateClassOrInterfaceType);
        classDecl.findAll(ConstructorDeclaration.class).forEach(this::interpolateConstructorDeclaration);
        classDecl.findAll(StringLiteralExpr.class).forEach(this::interpolateStringLiteral);
        classDecl.findAll(MethodReferenceExpr.class).forEach(this::interpolateMethodReference);

        return new GeneratedFile(GeneratedFileType.SOURCE, generatedFilePath(), cu.toString());
    }

    private void interpolateMethodReference(MethodReferenceExpr input) {
        input.setScope(new NameExpr(queryClassName));
    }

    private void interpolateClassOrInterfaceType(ClassOrInterfaceType input) {
        input.setName(interpolatedTypeNameFrom(input.getNameAsString()));
    }

    private void interpolateConstructorDeclaration(ConstructorDeclaration input) {
        input.setName(interpolatedTypeNameFrom(input.getNameAsString()));
    }

    private void interpolateStringLiteral(StringLiteralExpr input) {
        input.setString(input.getValue().replace("$name$", queryName));
    }

    private String interpolatedTypeNameFrom(String input) {
        return input.replace("$QueryType$", queryClassName)
                .replace("$DataType$", dataType);
    }
}

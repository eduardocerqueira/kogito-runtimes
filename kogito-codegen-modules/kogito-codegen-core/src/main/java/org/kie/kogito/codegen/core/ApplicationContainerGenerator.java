package org.kie.kogito.codegen.core;

import java.util.ArrayList;
import java.util.List;

import org.drools.codegen.common.GeneratedFile;
import org.drools.codegen.common.GeneratedFileType;
import org.kie.kogito.codegen.api.context.KogitoBuildContext;
import org.kie.kogito.codegen.api.template.InvalidTemplateException;
import org.kie.kogito.codegen.api.template.TemplatedGenerator;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.expr.ObjectCreationExpr;
import com.github.javaparser.ast.expr.ThisExpr;

import static org.kie.kogito.codegen.core.ApplicationGenerator.APPLICATION_CLASS_NAME;

public class ApplicationContainerGenerator {

    private static final GeneratedFileType APPLICATION_TYPE = GeneratedFileType.of("APPLICATION", GeneratedFileType.Category.SOURCE);

    private final TemplatedGenerator templatedGenerator;
    private final KogitoBuildContext context;

    private List<String> sections = new ArrayList<>();

    public ApplicationContainerGenerator(KogitoBuildContext context) {
        this.templatedGenerator = TemplatedGenerator.builder()
                .build(context, APPLICATION_CLASS_NAME);

        this.context = context;
    }

    public ApplicationContainerGenerator withSections(List<String> sections) {
        this.sections = sections;
        return this;
    }

    protected CompilationUnit getCompilationUnitOrThrow() {
        CompilationUnit compilationUnit = templatedGenerator.compilationUnitOrThrow();

        ClassOrInterfaceDeclaration cls = compilationUnit
                .findFirst(ClassOrInterfaceDeclaration.class)
                .orElseThrow(() -> new InvalidTemplateException(
                        templatedGenerator,
                        "Compilation unit doesn't contain a class or interface declaration!"));

        // Add explicit initialization when no DI
        if (!context.hasDI()) {
            initEngines(getLoadEnginesMethod(cls), sections);
        }

        cls.getMembers().sort(new BodyDeclarationComparator());

        return compilationUnit;
    }

    public GeneratedFile generate() {
        return new GeneratedFile(APPLICATION_TYPE,
                templatedGenerator.generatedFilePath(),
                getCompilationUnitOrThrow().toString());
    }

    private MethodCallExpr getLoadEnginesMethod(ClassOrInterfaceDeclaration cls) {
        return cls.findFirst(MethodCallExpr.class, mtd -> "loadEngines".equals(mtd.getNameAsString()))
                .orElseThrow(() -> new InvalidTemplateException(
                        templatedGenerator,
                        "Impossible to find loadEngines invocation"));
    }

    /**
     * For each section it produces a new instance follow naming convention and add it to methodCallExpr
     * e.g. section: Processes
     * produce:
     * e.g.: new Processes(this)
     * 
     * @param methodCallExpr
     * @param sections
     */
    private void initEngines(MethodCallExpr methodCallExpr, List<String> sections) {
        sections.stream()
                .map(section -> new ObjectCreationExpr()
                        .setType(section)
                        .addArgument(new ThisExpr()))
                .forEach(methodCallExpr::addArgument);
    }
}

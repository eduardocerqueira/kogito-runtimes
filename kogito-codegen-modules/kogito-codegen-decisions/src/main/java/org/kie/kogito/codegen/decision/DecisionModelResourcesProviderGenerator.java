package org.kie.kogito.codegen.decision;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import org.kie.kogito.KogitoGAV;
import org.kie.kogito.codegen.api.context.KogitoBuildContext;
import org.kie.kogito.codegen.api.context.impl.JavaKogitoBuildContext;
import org.kie.kogito.codegen.api.template.InvalidTemplateException;
import org.kie.kogito.codegen.api.template.TemplatedGenerator;
import org.kie.kogito.decision.DecisionModelMetadata;
import org.kie.kogito.dmn.DefaultDecisionModelResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.expr.ObjectCreationExpr;
import com.github.javaparser.ast.expr.StringLiteralExpr;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.type.ClassOrInterfaceType;

import static org.kie.kogito.codegen.core.CodegenUtils.newObject;
import static org.kie.kogito.codegen.decision.ReadResourceUtil.getReadResourceMethod;

public class DecisionModelResourcesProviderGenerator {

    private static final Logger LOGGER = LoggerFactory.getLogger(DecisionModelResourcesProviderGenerator.class);

    private final KogitoBuildContext context;
    private final String applicationCanonicalName;
    private final List<DMNResource> resources;
    private final TemplatedGenerator generator;

    public DecisionModelResourcesProviderGenerator(final KogitoBuildContext context,
            final String applicationCanonicalName,
            final List<DMNResource> resources) {
        this.context = context;
        this.applicationCanonicalName = applicationCanonicalName;
        this.resources = resources;
        this.generator = TemplatedGenerator.builder()
                .withFallbackContext(JavaKogitoBuildContext.CONTEXT_NAME)
                .build(context, "DecisionModelResourcesProvider");
    }

    public String generate() {
        final CompilationUnit compilationUnit = generator.compilationUnitOrThrow();

        final ClassOrInterfaceDeclaration clazz = compilationUnit
                .findFirst(ClassOrInterfaceDeclaration.class)
                .orElseThrow(() -> new NoSuchElementException("Compilation unit doesn't contain a class or interface declaration!"));

        if (context.hasDI()) {
            context.getDependencyInjectionAnnotator().withSingletonComponent(clazz);
        }

        if (context.getAddonsConfig().useTracing()) {
            setupResourcesVariable(clazz);
        }
        return compilationUnit.toString();
    }

    public String generatedFilePath() {
        return generator.generatedFilePath();
    }

    private void setupResourcesVariable(final ClassOrInterfaceDeclaration typeDeclaration) {
        final List<MethodDeclaration> getResourcesMethods = typeDeclaration.getMethodsBySignature("getResources");
        final ClassOrInterfaceType applicationClass = StaticJavaParser.parseClassOrInterfaceType(applicationCanonicalName);

        if (getResourcesMethods.size() != 1) {
            throw new InvalidTemplateException(
                    generator,
                    "A \"getResources()\" method was not found");
        }
        final MethodDeclaration getResourcesMethod = getResourcesMethods.get(0);
        final BlockStmt body = getResourcesMethod.getBody().orElseThrow(() -> new RuntimeException("Can't find the body of the \"get()\" method."));
        final VariableDeclarator resourcePathsVariable =
                getResourcesMethod.findFirst(VariableDeclarator.class).orElseThrow(() -> new RuntimeException("Can't find a variable declaration in the \"get()\" method."));

        if (!context.getGAV().isPresent()) {
            LOGGER.error("Impossible to obtain project group-artifact-id, using empty value");
        }
        KogitoGAV gav = context.getGAV().orElse(KogitoGAV.EMPTY_GAV);

        for (DMNResource resource : resources) {
            final MethodCallExpr add = new MethodCallExpr(resourcePathsVariable.getNameAsExpression(), "add");
            final MethodCallExpr getResAsStream = getReadResourceMethod(applicationClass, resource.getCollectedResource());
            final MethodCallExpr isr = new MethodCallExpr("readResource").addArgument(getResAsStream);
            add.addArgument(newObject(DefaultDecisionModelResource.class,
                    newGAV(gav),
                    new StringLiteralExpr(resource.getDmnModel().getNamespace()),
                    new StringLiteralExpr(resource.getDmnModel().getName()),
                    makeDecisionModelMetadata(resource),
                    isr));
            body.addStatement(body.getStatements().size() - 1, add);
        }
    }

    private ObjectCreationExpr newGAV(KogitoGAV gav) {
        return newObject(KogitoGAV.class,
                new StringLiteralExpr(gav.getGroupId()),
                new StringLiteralExpr(gav.getArtifactId()),
                new StringLiteralExpr(gav.getVersion()));
    }

    private ObjectCreationExpr makeDecisionModelMetadata(DMNResource resource) {
        return newObject(DecisionModelMetadata.class, new StringLiteralExpr(extractModelVersion(resource)));
    }

    private String extractModelVersion(DMNResource resource) {
        Set<String> definitions = new HashSet<>(resource.getDmnModel().getDefinitions().getNsContext().values());
        definitions.retainAll(Arrays.asList(org.kie.dmn.model.v1_1.KieDMNModelInstrumentedBase.URI_DMN,
                org.kie.dmn.model.v1_2.KieDMNModelInstrumentedBase.URI_DMN,
                org.kie.dmn.model.v1_3.KieDMNModelInstrumentedBase.URI_DMN));

        if (definitions.size() != 1) {
            LOGGER.error("Could not extract DMN version from DMN model {}", resource.getDmnModel().getName());
            throw new IllegalStateException("The DMN model does not contain a unique model version in the metadata.");
        }
        return definitions.iterator().next();
    }

}

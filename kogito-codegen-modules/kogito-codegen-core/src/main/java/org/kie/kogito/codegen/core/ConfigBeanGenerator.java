package org.kie.kogito.codegen.core;

import java.util.Collections;
import java.util.Optional;

import org.kie.kogito.KogitoGAV;
import org.kie.kogito.codegen.api.context.KogitoBuildContext;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.expr.NullLiteralExpr;
import com.github.javaparser.ast.expr.StringLiteralExpr;

import static org.kie.kogito.codegen.core.CodegenUtils.newObject;

public class ConfigBeanGenerator extends AbstractConfigGenerator {

    protected String GAV_TEMPLATE = "$gav$";

    public ConfigBeanGenerator(KogitoBuildContext context) {
        super(context, "ConfigBean");
    }

    @Override
    protected CompilationUnit toCompilationUnit() {
        CompilationUnit compilationUnit = templatedGenerator.compilationUnitOrThrow();

        Optional<MethodCallExpr> setGavMethod = compilationUnit
                .findFirst(MethodDeclaration.class, md -> "init".equals(md.getNameAsString()))
                .flatMap(md -> md.findFirst(MethodCallExpr.class, mc -> "setGav".equals(mc.getNameAsString())));

        setGavMethod.ifPresent(gav -> CodegenUtils.interpolateArguments(gav, Collections.singletonMap(GAV_TEMPLATE, newGavOrNull())));

        return compilationUnit;
    }

    private Expression newGavOrNull() {
        return context.getGAV()
                .map(this::newGAV)
                .orElseGet(NullLiteralExpr::new);
    }

    private Expression newGAV(KogitoGAV gav) {
        return newObject(KogitoGAV.class,
                new StringLiteralExpr(gav.getGroupId()),
                new StringLiteralExpr(gav.getArtifactId()),
                new StringLiteralExpr(gav.getVersion()));
    }
}

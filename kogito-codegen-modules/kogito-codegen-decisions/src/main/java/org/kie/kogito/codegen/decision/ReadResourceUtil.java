package org.kie.kogito.codegen.decision;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.drools.util.IoUtils;
import org.kie.kogito.codegen.api.io.CollectedResource;

import com.github.javaparser.ast.expr.FieldAccessExpr;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.expr.NameExpr;
import com.github.javaparser.ast.expr.StringLiteralExpr;
import com.github.javaparser.ast.type.ClassOrInterfaceType;

class ReadResourceUtil {

    private ReadResourceUtil() {
        //Utility class cannot be instantiated.
    }

    public static MethodCallExpr getReadResourceMethod(ClassOrInterfaceType applicationClass, CollectedResource resource) {
        if (resource.basePath().toString().endsWith(".jar")) {
            return new MethodCallExpr(
                    new MethodCallExpr(new NameExpr(IoUtils.class.getCanonicalName() + ".class"), "getClassLoader"),
                    "getResourceAsStream").addArgument(new StringLiteralExpr(getDecisionModelJarResourcePath(resource)));
        }

        return new MethodCallExpr(new FieldAccessExpr(applicationClass.getNameAsExpression(), "class"), "getResourceAsStream")
                .addArgument(new StringLiteralExpr(getDecisionModelRelativeResourcePath(resource)));
    }

    private static String getDecisionModelJarResourcePath(CollectedResource resource) {
        return resource.resource().getSourcePath();
    }

    private static String getDecisionModelRelativeResourcePath(CollectedResource resource) {
        String source = getDecisionModelJarResourcePath(resource);
        try {
            Path sourcePath = Paths.get(source).toAbsolutePath().toRealPath();
            Path relativizedPath = resource.basePath().toAbsolutePath().toRealPath().relativize(sourcePath);
            return "/" + relativizedPath.toString().replace(File.separatorChar, '/');
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}

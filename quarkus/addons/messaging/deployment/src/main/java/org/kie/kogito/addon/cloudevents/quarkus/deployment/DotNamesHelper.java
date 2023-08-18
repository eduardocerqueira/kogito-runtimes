package org.kie.kogito.addon.cloudevents.quarkus.deployment;

import org.jboss.jandex.DotName;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.PackageDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;

import io.quarkus.arc.impl.ComputingCache;

public class DotNamesHelper {

    private DotNamesHelper() {
    }

    private static ComputingCache<String, DotName> dotNamesMap = new ComputingCache<>(DotNamesHelper::createDotName);

    public static DotName createDotName(String name) {
        int lastDot = name.lastIndexOf('.');
        if (lastDot < 0) {
            return DotName.createComponentized(null, name);
        }
        String prefix = name.substring(0, lastDot);
        DotName prefixName = dotNamesMap.getValue(prefix);
        String local = name.substring(lastDot + 1);
        return DotName.createComponentized(prefixName, local);
    }

    public static DotName createAnnotationName(EventGenerator generator) {
        return createDotName(generator.getFullAnnotationName().orElseThrow(() -> new IllegalArgumentException("No annotation name for bean " + generator.getClassName())));
    }

    public static DotName createClassName(EventGenerator generator) {
        return createDotName(generator.getPackageName() + '.' + generator.getClassName());
    }

    public static DotName createDotName(CompilationUnit cu) {
        return DotNamesHelper.createDotName(cu.getPackageDeclaration().map(PackageDeclaration::getNameAsString).orElse("") + "."
                + cu.findFirst(ClassOrInterfaceDeclaration.class).orElseThrow(() -> new IllegalStateException("cannnot find class")).getNameAsString());
    }

}

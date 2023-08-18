package org.kie.kogito.codegen.rules;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.kie.internal.ruleunit.RuleUnitDescription;

import com.github.javaparser.ast.type.Type;

import static com.github.javaparser.StaticJavaParser.parseClassOrInterfaceType;
import static org.drools.model.codegen.execmodel.generator.DrlxParseUtil.toClassOrInterfaceType;

public class RuleCodegenUtils {

    private RuleCodegenUtils() {
        // utility class
    }

    public static void setGeneric(Type type, RuleUnitDescription ruleUnit) {
        type.asClassOrInterfaceType().setTypeArguments(toClassOrInterfaceType(ruleUnit.getCanonicalName()));
    }

    public static void setGeneric(Type type, String typeArgument) {
        type.asClassOrInterfaceType().setTypeArguments(parseClassOrInterfaceType(toNonPrimitiveType(typeArgument)));
    }

    public static String toNonPrimitiveType(String type) {
        switch (type) {
            case "int":
                return "Integer";
            case "long":
                return "Long";
            case "double":
                return "Double";
            case "float":
                return "Float";
            case "short":
                return "Short";
            case "byte":
                return "Byte";
            case "char":
                return "Character";
            case "boolean":
                return "Boolean";
            default:
                return type;
        }
    }

    public static String toCamelCase(String inputString) {
        return Stream.of(inputString.split(" "))
                .map(s -> s.length() > 1 ? s.substring(0, 1).toUpperCase() + s.substring(1) : s.substring(0, 1).toUpperCase())
                .collect(Collectors.joining());
    }

    public static String toKebabCase(String inputString) {
        return inputString.replaceAll("(.)(\\p{Upper})", "$1-$2").toLowerCase();
    }
}

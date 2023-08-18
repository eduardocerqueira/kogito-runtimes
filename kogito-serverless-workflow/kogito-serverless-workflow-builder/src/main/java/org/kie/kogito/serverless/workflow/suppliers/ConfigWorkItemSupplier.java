package org.kie.kogito.serverless.workflow.suppliers;

import java.util.function.Supplier;

import org.jbpm.compiler.canonical.descriptors.ExpressionUtils;
import org.kie.kogito.serverless.workflow.workitemparams.ConfigWorkItemResolver;

import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.ObjectCreationExpr;

import static com.github.javaparser.StaticJavaParser.parseClassOrInterfaceType;

public class ConfigWorkItemSupplier<T> extends ConfigWorkItemResolver<T> implements Supplier<Expression> {

    private final ObjectCreationExpr expression;

    public ConfigWorkItemSupplier(String key, Class<T> clazz, T defaultValue) {
        super(key, clazz, defaultValue);
        this.expression =
                ExpressionUtils.getObjectCreationExpr(
                        parseClassOrInterfaceType(ConfigWorkItemResolver.class.getCanonicalName()).setTypeArguments(parseClassOrInterfaceType(clazz.getCanonicalName())), key, clazz, defaultValue);
    }

    @Override
    public Expression get() {
        return expression;
    }

}

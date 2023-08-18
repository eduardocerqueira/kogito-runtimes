package org.kie.kogito.serverless.workflow.suppliers;

import java.util.function.Supplier;
import java.util.function.UnaryOperator;

import org.jbpm.compiler.canonical.descriptors.ExpressionUtils;
import org.kie.kogito.serverless.workflow.workitemparams.ConfigSuppliedWorkItemResolver;

import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.ObjectCreationExpr;

import static com.github.javaparser.StaticJavaParser.parseClassOrInterfaceType;

public class ConfigSuppliedWorkItemSupplier<T> extends ConfigSuppliedWorkItemResolver<T> implements Supplier<Expression> {

    private final ObjectCreationExpr expression;

    public ConfigSuppliedWorkItemSupplier(String key, Class<T> clazz, T defaultValue, UnaryOperator<T> transformer, Expression transformerExpr) {
        super(key, clazz, defaultValue, transformer);
        this.expression =
                ExpressionUtils.getObjectCreationExpr(
                        parseClassOrInterfaceType(ConfigSuppliedWorkItemResolver.class.getCanonicalName()).setTypeArguments(parseClassOrInterfaceType(clazz.getCanonicalName())), key, clazz,
                        defaultValue, transformerExpr);
    }

    @Override
    public Expression get() {
        return expression;
    }
}

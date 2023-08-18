package org.kie.kogito.serverless.workflow.suppliers;

import java.util.function.Supplier;

import org.jbpm.compiler.canonical.descriptors.ExpressionUtils;
import org.kogito.workitem.rest.auth.ClientOAuth2AuthDecorator;

import com.github.javaparser.ast.expr.Expression;

public class ClientOAuth2AuthDecoratorSupplier extends ClientOAuth2AuthDecorator implements Supplier<Expression> {

    private final Expression expression;

    public ClientOAuth2AuthDecoratorSupplier(String tokenUrl, String refreshUrl) {
        super(tokenUrl, refreshUrl);
        this.expression = ExpressionUtils.getObjectCreationExpr(ClientOAuth2AuthDecorator.class, tokenUrl, refreshUrl);
    }

    @Override
    public Expression get() {
        return expression;
    }

}

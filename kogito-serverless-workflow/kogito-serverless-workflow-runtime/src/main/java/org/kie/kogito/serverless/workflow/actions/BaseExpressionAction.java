package org.kie.kogito.serverless.workflow.actions;

import org.jbpm.process.instance.impl.Action;
import org.kie.kogito.internal.process.runtime.KogitoProcessContext;
import org.kie.kogito.process.expr.Expression;
import org.kie.kogito.process.expr.ExpressionHandlerFactory;

import static org.kie.kogito.serverless.workflow.actions.ActionUtils.getJsonNode;

public abstract class BaseExpressionAction implements Action {

    protected final Expression expr;
    protected final String modelVar;

    public BaseExpressionAction(String lang, String expr, String inputVar) {
        this.expr = ExpressionHandlerFactory.get(lang, expr);
        this.modelVar = inputVar;
    }

    protected final <T> T evaluate(KogitoProcessContext context, Class<T> resultClass) {
        return expr.eval(getJsonNode(context, modelVar), resultClass, context);
    }
}

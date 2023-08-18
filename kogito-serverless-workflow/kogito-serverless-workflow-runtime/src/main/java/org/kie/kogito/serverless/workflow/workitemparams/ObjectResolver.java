package org.kie.kogito.serverless.workflow.workitemparams;

import org.kie.kogito.internal.process.runtime.KogitoWorkItem;
import org.kie.kogito.jackson.utils.JsonObjectUtils;

public class ObjectResolver extends ExpressionWorkItemResolver<Object> {

    public ObjectResolver(String exprLang, Object expr, String paramName) {
        super(exprLang, expr, paramName);
    }

    @Override
    public Object apply(KogitoWorkItem workItem) {
        return JsonObjectUtils.toJavaValue(evalExpression(workItem));
    }
}

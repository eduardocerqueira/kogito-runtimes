package org.kie.kogito.serverless.workflow.workitemparams;

import java.util.Map;

import org.kie.kogito.internal.process.runtime.KogitoWorkItem;
import org.kie.kogito.jackson.utils.JsonObjectUtils;
import org.kie.kogito.process.workitems.WorkParametersFactory;

import static java.util.Collections.singletonMap;
import static org.kie.kogito.serverless.workflow.SWFConstants.CONTENT_DATA;

public class ExpressionParametersFactory extends ExpressionWorkItemResolver<Map<String, Object>> implements WorkParametersFactory {

    public ExpressionParametersFactory(String exprLang, Object expr, String paramName) {
        super(exprLang, expr, paramName);
    }

    @Override
    public Map<String, Object> apply(KogitoWorkItem workItem) {
        Object obj = JsonObjectUtils.toJavaValue(super.evalExpression(workItem));
        return obj instanceof Map ? (Map<String, Object>) obj : singletonMap(CONTENT_DATA, obj);
    }
}

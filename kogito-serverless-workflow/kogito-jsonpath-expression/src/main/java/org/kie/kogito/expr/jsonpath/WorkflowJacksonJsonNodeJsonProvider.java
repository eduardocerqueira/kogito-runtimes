package org.kie.kogito.expr.jsonpath;

import java.util.function.Function;

import org.kie.kogito.internal.process.runtime.KogitoProcessContext;
import org.kie.kogito.serverless.workflow.utils.ExpressionHandlerUtils;

import com.jayway.jsonpath.spi.json.JacksonJsonNodeJsonProvider;

public class WorkflowJacksonJsonNodeJsonProvider extends JacksonJsonNodeJsonProvider {

    private KogitoProcessContext context;

    public WorkflowJacksonJsonNodeJsonProvider(KogitoProcessContext context) {
        this.context = context;
    }

    @Override
    public Object getMapValue(Object obj, String key) {
        if (obj instanceof Function) {
            return ((Function<String, Object>) obj).apply(key);
        } else {
            switch (key) {
                case "$" + ExpressionHandlerUtils.SECRET_MAGIC:
                    return (Function<String, Object>) ExpressionHandlerUtils::getSecret;
                case "$" + ExpressionHandlerUtils.CONTEXT_MAGIC:
                    return ExpressionHandlerUtils.getContextFunction(context);
                case "$" + ExpressionHandlerUtils.CONST_MAGIC:
                    return ExpressionHandlerUtils.getConstants(context);
                default:
                    return super.getMapValue(obj, key);
            }
        }
    }

    @Override
    public boolean isMap(Object obj) {
        return super.isMap(obj) || obj instanceof Function;
    }
}

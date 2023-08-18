package org.kie.kogito.serverless.workflow.actions;

import java.util.function.Supplier;

import org.jbpm.process.instance.impl.actions.ProduceEventAction;
import org.kie.kogito.event.impl.MessageProducer;
import org.kie.kogito.internal.process.runtime.KogitoProcessContext;
import org.kie.kogito.jackson.utils.JsonObjectUtils;
import org.kie.kogito.jackson.utils.ObjectMapperFactory;
import org.kie.kogito.process.expr.Expression;
import org.kie.kogito.process.expr.ExpressionHandlerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;

public class SWFProduceEventAction extends ProduceEventAction<JsonNode> {
    private static final long serialVersionUID = 1L;

    protected final String exprLang;
    protected final String data;

    public SWFProduceEventAction(String triggerName, String varName, Supplier<MessageProducer<JsonNode>> supplier, String exprLang, String data) {
        super(triggerName, varName, supplier);
        this.exprLang = exprLang;
        this.data = data;
    }

    @Override
    protected JsonNode getObject(Object object, KogitoProcessContext context) {
        Expression expr = null;
        JsonNode value = null;
        if (data != null) {
            expr = ExpressionHandlerFactory.get(exprLang, data);
            if (!expr.isValid()) {
                try {
                    value = ObjectMapperFactory.get().readTree(data);
                } catch (JsonProcessingException e) {
                    throw new IllegalArgumentException("Data " + data + " is not valid json not valid expression");
                }
            }
        }
        if (value != null) {
            return value;
        } else if (expr != null) {
            return expr.eval(object, JsonNode.class, context);
        } else {
            return JsonObjectUtils.fromValue(object);
        }
    }
}

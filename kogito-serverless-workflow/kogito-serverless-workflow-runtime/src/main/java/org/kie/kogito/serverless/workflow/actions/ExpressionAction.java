package org.kie.kogito.serverless.workflow.actions;

import org.kie.kogito.internal.process.runtime.KogitoProcessContext;

import com.fasterxml.jackson.databind.JsonNode;

public class ExpressionAction extends BaseExpressionAction {

    protected final String outputVar;

    public ExpressionAction(String lang, String expr, String inputVar, String outputVar) {
        super(lang, expr, inputVar);
        this.outputVar = outputVar;
    }

    @Override
    public void execute(KogitoProcessContext context) throws Exception {
        JsonNode result = evaluate(context, JsonNode.class);
        context.setVariable(outputVar, result);
    }
}

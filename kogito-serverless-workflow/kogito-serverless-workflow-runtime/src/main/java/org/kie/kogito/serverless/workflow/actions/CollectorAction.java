package org.kie.kogito.serverless.workflow.actions;

import org.kie.kogito.internal.process.runtime.KogitoProcessContext;

public class CollectorAction extends BaseExpressionAction {

    private String outputVar;

    public CollectorAction(String lang, String expr, String inputVar, String outputVar) {
        super(lang, expr, inputVar);
        this.outputVar = outputVar;
    }

    @Override
    public void execute(KogitoProcessContext context) throws Exception {
        expr.assign(ActionUtils.getJsonNode(context, modelVar), ActionUtils.getJsonNode(context, outputVar), context);
    }
}

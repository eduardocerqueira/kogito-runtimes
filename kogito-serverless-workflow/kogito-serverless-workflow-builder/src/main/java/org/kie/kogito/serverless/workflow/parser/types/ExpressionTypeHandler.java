package org.kie.kogito.serverless.workflow.parser.types;

import org.jbpm.ruleflow.core.RuleFlowNodeContainerFactory;
import org.jbpm.ruleflow.core.factory.ActionNodeFactory;
import org.kie.kogito.serverless.workflow.parser.VariableInfo;
import org.kie.kogito.serverless.workflow.suppliers.ExpressionActionSupplier;

import io.serverlessworkflow.api.Workflow;
import io.serverlessworkflow.api.functions.FunctionDefinition;
import io.serverlessworkflow.api.functions.FunctionRef;

public class ExpressionTypeHandler extends ActionTypeHandler {

    @Override
    protected <T extends RuleFlowNodeContainerFactory<T, ?>> ActionNodeFactory<T> fillAction(Workflow workflow,
            ActionNodeFactory<T> node,
            FunctionDefinition functionDef,
            FunctionRef functionRef,
            VariableInfo varInfo) {
        return node.action(ExpressionActionSupplier.of(workflow, functionDef.getOperation())
                .withVarNames(varInfo.getInputVar(), varInfo.getOutputVar()).build());
    }

    @Override
    public String type() {
        return FunctionDefinition.Type.EXPRESSION.toString();
    }

    @Override
    public boolean isCustom() {
        return false;
    }
}

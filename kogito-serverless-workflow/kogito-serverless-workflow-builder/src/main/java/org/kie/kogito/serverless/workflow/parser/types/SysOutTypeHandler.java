package org.kie.kogito.serverless.workflow.parser.types;

import org.jbpm.ruleflow.core.RuleFlowNodeContainerFactory;
import org.jbpm.ruleflow.core.factory.ActionNodeFactory;
import org.kie.kogito.serverless.workflow.parser.FunctionTypeHandlerFactory;
import org.kie.kogito.serverless.workflow.parser.VariableInfo;
import org.kie.kogito.serverless.workflow.suppliers.SysoutActionSupplier;

import io.serverlessworkflow.api.Workflow;
import io.serverlessworkflow.api.functions.FunctionDefinition;
import io.serverlessworkflow.api.functions.FunctionRef;

import static org.kie.kogito.serverless.workflow.parser.handlers.ActionNodeUtils.checkArgs;

public class SysOutTypeHandler extends ActionTypeHandler {

    public static final String SYSOUT_TYPE = "sysout";
    public static final String SYSOUT_TYPE_PARAM = "message";

    @Override
    protected <T extends RuleFlowNodeContainerFactory<T, ?>> ActionNodeFactory<T> fillAction(Workflow workflow,
            ActionNodeFactory<T> node,
            FunctionDefinition functionDef,
            FunctionRef functionRef,
            VariableInfo varInfo) {
        checkArgs(functionRef, SYSOUT_TYPE_PARAM);
        return node.action(new SysoutActionSupplier(workflow.getExpressionLang(), functionRef.getArguments().get(SYSOUT_TYPE_PARAM).asText(), varInfo.getInputVar(),
                FunctionTypeHandlerFactory.trimCustomOperation(functionDef)));
    }

    @Override
    public String type() {
        return SYSOUT_TYPE;
    }
}

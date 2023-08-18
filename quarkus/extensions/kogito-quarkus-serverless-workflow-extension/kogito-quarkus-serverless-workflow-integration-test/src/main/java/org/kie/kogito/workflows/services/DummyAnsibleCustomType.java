package org.kie.kogito.workflows.services;

import org.jbpm.ruleflow.core.RuleFlowNodeContainerFactory;
import org.jbpm.ruleflow.core.factory.ActionNodeFactory;
import org.kie.kogito.jackson.utils.ObjectMapperFactory;
import org.kie.kogito.serverless.workflow.parser.VariableInfo;
import org.kie.kogito.serverless.workflow.parser.types.ActionTypeHandler;
import org.kie.kogito.serverless.workflow.suppliers.InjectActionSupplier;

import io.serverlessworkflow.api.Workflow;
import io.serverlessworkflow.api.functions.FunctionDefinition;
import io.serverlessworkflow.api.functions.FunctionRef;

public class DummyAnsibleCustomType extends ActionTypeHandler {

    @Override
    public String type() {
        return "ansible";
    }

    @Override
    protected <T extends RuleFlowNodeContainerFactory<T, ?>> ActionNodeFactory<T> fillAction(Workflow workflow,
            ActionNodeFactory<T> node,
            FunctionDefinition functionDef,
            FunctionRef functionRef,
            VariableInfo varInfo) {
        return node.action(new InjectActionSupplier(ObjectMapperFactory.get().createObjectNode().put("name", "John")));
    }
}

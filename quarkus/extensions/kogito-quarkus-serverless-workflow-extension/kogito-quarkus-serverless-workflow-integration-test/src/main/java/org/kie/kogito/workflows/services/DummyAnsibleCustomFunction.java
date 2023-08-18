package org.kie.kogito.workflows.services;

import org.jbpm.ruleflow.core.RuleFlowNodeContainerFactory;
import org.jbpm.ruleflow.core.factory.ActionNodeFactory;
import org.kie.kogito.jackson.utils.ObjectMapperFactory;
import org.kie.kogito.serverless.workflow.functions.ActionFunctionNamespace;
import org.kie.kogito.serverless.workflow.parser.VariableInfo;
import org.kie.kogito.serverless.workflow.suppliers.InjectActionSupplier;

import io.serverlessworkflow.api.Workflow;
import io.serverlessworkflow.api.functions.FunctionRef;

public class DummyAnsibleCustomFunction extends ActionFunctionNamespace {

    @Override
    public String namespace() {
        return "ansible";
    }

    @Override
    protected <T extends RuleFlowNodeContainerFactory<T, ?>> ActionNodeFactory<T> fillAction(Workflow workflow,
            ActionNodeFactory<T> node,
            FunctionRef functionRef,
            VariableInfo varInfo) {
        return node.action(new InjectActionSupplier(ObjectMapperFactory.get().createObjectNode().put("name", "John")));
    }
}

package org.kie.kogito.addons.quarkus.camel.deployment;

import org.jbpm.ruleflow.core.RuleFlowNodeContainerFactory;
import org.jbpm.ruleflow.core.factory.WorkItemNodeFactory;
import org.kie.kogito.addons.quarkus.camel.runtime.CamelConstants;
import org.kie.kogito.serverless.workflow.parser.ParserContext;
import org.kie.kogito.serverless.workflow.parser.types.WorkItemTypeHandler;

import io.serverlessworkflow.api.Workflow;
import io.serverlessworkflow.api.functions.FunctionDefinition;
import io.serverlessworkflow.api.functions.FunctionRef;

import static org.kie.kogito.addons.quarkus.camel.runtime.CamelCustomWorkItemHandler.NAME;
import static org.kie.kogito.addons.quarkus.camel.runtime.CamelCustomWorkItemHandler.OPERATION;
import static org.kie.kogito.serverless.workflow.parser.FunctionTypeHandlerFactory.trimCustomOperation;

/**
 * Implementation for the custom Camel Function Definition
 */
public class CamelWorkItemTypeHandler extends WorkItemTypeHandler {

    @Override
    protected <T extends RuleFlowNodeContainerFactory<T, ?>> WorkItemNodeFactory<T> fillWorkItemHandler(Workflow workflow, ParserContext context, WorkItemNodeFactory<T> node,
            FunctionDefinition functionDef) {
        return node.workName(NAME).metaData(OPERATION, trimCustomOperation(functionDef));
    }

    @Override
    protected void validateArgs(FunctionRef ref) {
        CamelFunctionStaticValidator.validateFunctionRef(ref);
    }

    @Override
    public String type() {
        return CamelConstants.NAMESPACE;
    }

}

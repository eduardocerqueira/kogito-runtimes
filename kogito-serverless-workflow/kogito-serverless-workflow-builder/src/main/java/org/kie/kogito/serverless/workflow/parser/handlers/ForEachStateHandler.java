package org.kie.kogito.serverless.workflow.parser.handlers;

import org.jbpm.process.core.datatype.impl.type.ObjectDataType;
import org.jbpm.ruleflow.core.Metadata;
import org.jbpm.ruleflow.core.RuleFlowNodeContainerFactory;
import org.jbpm.ruleflow.core.factory.ForEachNodeFactory;
import org.kie.kogito.serverless.workflow.parser.ParserContext;
import org.kie.kogito.serverless.workflow.suppliers.CollectorActionSupplier;

import com.fasterxml.jackson.databind.JsonNode;

import io.serverlessworkflow.api.Workflow;
import io.serverlessworkflow.api.states.ForEachState;

import static org.jbpm.workflow.instance.node.ForEachNodeInstance.TEMP_OUTPUT_VAR;
import static org.kie.kogito.serverless.workflow.parser.ServerlessWorkflowParser.DEFAULT_WORKFLOW_VAR;
import static org.kie.kogito.serverless.workflow.utils.KogitoProcessContextResolver.FOR_EACH_OUTPUT_VARIABLE;

public class ForEachStateHandler extends CompositeContextNodeHandler<ForEachState> {

    protected ForEachStateHandler(ForEachState state, Workflow workflow, ParserContext parserContext) {
        super(state, workflow, parserContext);
    }

    @Override
    protected MakeNodeResult makeNode(RuleFlowNodeContainerFactory<?, ?> factory) {
        ForEachNodeFactory<?> result =
                factory.forEachNode(parserContext.newId()).sequential(false).waitForCompletion(true).expressionLanguage(workflow.getExpressionLang()).collectionExpression(state.getInputCollection())
                        .outputVariable(FOR_EACH_OUTPUT_VARIABLE, new ObjectDataType(JsonNode.class))
                        .metaData(Metadata.VARIABLE, DEFAULT_WORKFLOW_VAR)
                        .tempVariable(TEMP_OUTPUT_VAR, new ObjectDataType(JsonNode.class));
        if (state.getIterationParam() != null) {
            result.variable(state.getIterationParam(), new ObjectDataType(JsonNode.class));
        }
        if (state.getOutputCollection() != null) {
            result.completionAction(new CollectorActionSupplier(workflow.getExpressionLang(), state.getOutputCollection(), DEFAULT_WORKFLOW_VAR, TEMP_OUTPUT_VAR));
        }
        handleActions(result, state.getActions(), FOR_EACH_OUTPUT_VARIABLE, false);
        return new MakeNodeResult(result);
    }

    @Override
    public boolean usedForCompensation() {
        return state.isUsedForCompensation();
    }

}

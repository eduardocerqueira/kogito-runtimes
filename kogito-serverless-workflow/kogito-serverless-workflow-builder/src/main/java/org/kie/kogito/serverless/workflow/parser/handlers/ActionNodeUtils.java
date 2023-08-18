package org.kie.kogito.serverless.workflow.parser.handlers;

import org.jbpm.ruleflow.core.RuleFlowNodeContainerFactory;
import org.jbpm.ruleflow.core.factory.ActionNodeFactory;
import org.kie.kogito.serverless.workflow.parser.ParserContext;

import com.fasterxml.jackson.databind.JsonNode;

import io.serverlessworkflow.api.functions.FunctionDefinition;
import io.serverlessworkflow.api.functions.FunctionRef;

public class ActionNodeUtils {

    public static <T extends RuleFlowNodeContainerFactory<T, ?>> ActionNodeFactory<T> actionNode(RuleFlowNodeContainerFactory<T, ?> embeddedSubProcess, ParserContext context,
            FunctionDefinition functionDef) {
        return embeddedSubProcess.actionNode(context.newId()).name(functionDef.getName());
    }

    public static void checkArgs(FunctionRef functionRef, String... requiredArgs) {
        JsonNode args = functionRef.getArguments();
        if (args == null) {
            throw new IllegalArgumentException("Arguments cannot be null for function " + functionRef.getRefName());
        }
        for (String arg : requiredArgs) {
            if (!args.has(arg)) {
                throw new IllegalArgumentException("Missing mandatory " + arg + " argument for function " + functionRef.getRefName());
            }
        }
    }

    private ActionNodeUtils() {
    }
}

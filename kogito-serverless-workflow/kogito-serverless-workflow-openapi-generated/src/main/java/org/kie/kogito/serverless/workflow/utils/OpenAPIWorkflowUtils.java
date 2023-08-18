package org.kie.kogito.serverless.workflow.utils;

import org.drools.util.StringUtils;

import io.serverlessworkflow.api.functions.FunctionDefinition;
import io.serverlessworkflow.api.functions.FunctionDefinition.Type;

import static org.kie.kogito.serverless.workflow.utils.ServerlessWorkflowUtils.getValidIdentifier;
import static org.kie.kogito.serverless.workflow.utils.ServerlessWorkflowUtils.removeExt;

public class OpenAPIWorkflowUtils {
    public static String getOpenApiWorkItemName(String fileName, String methodName) {
        return removeExt(fileName) + '_' + methodName;
    }

    public static String getOpenApiClassName(String fileName, String methodName) {
        return StringUtils.ucFirst(getValidIdentifier(removeExt(fileName.toLowerCase())) + '_' + methodName);
    }

    public static boolean isOpenApiOperation(FunctionDefinition function) {
        return function.getType() == Type.REST && function.getOperation() != null && function.getOperation().contains(ServerlessWorkflowUtils.OPERATION_SEPARATOR);
    }

    private OpenAPIWorkflowUtils() {
    }
}

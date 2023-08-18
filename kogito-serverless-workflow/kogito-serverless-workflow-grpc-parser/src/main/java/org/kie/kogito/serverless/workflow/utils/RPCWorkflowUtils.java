package org.kie.kogito.serverless.workflow.utils;

public class RPCWorkflowUtils {

    public static String getRPCClassName(String serviceName) {
        return "RPC_" + serviceName + "_WorkItemHandler";
    }

    private RPCWorkflowUtils() {
    }
}

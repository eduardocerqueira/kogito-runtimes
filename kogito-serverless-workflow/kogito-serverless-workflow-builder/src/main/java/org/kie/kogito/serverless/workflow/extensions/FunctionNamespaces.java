package org.kie.kogito.serverless.workflow.extensions;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.node.ArrayNode;

import io.serverlessworkflow.api.interfaces.Extension;

public class FunctionNamespaces implements Extension {

    public static final String FUNCTION_NAMESPACES = "workflow-function-namespaces";

    private ArrayNode namespaces;
    // this is a bug in workflow sdk, see https://github.com/serverlessworkflow/sdk-java/pull/207
    @JsonProperty("extensionid")
    private String extensionId;

    @Override
    public String getExtensionId() {
        return extensionId;
    }

    public ArrayNode getNamespaces() {
        return namespaces;
    }

}

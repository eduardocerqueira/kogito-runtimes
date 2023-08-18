package org.kie.kogito.serverless.workflow.extensions;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

import io.serverlessworkflow.api.interfaces.Extension;

public class URIDefinitions implements Extension {

    public static final String URI_DEFINITIONS = "workflow-uri-definitions";

    private String uri;
    private JsonNode definitions;
    // this is a bug in workflow sdk, see https://github.com/serverlessworkflow/sdk-java/pull/207
    @JsonProperty("extensionid")
    private String extensionId;

    @Override
    public String getExtensionId() {
        return extensionId;
    }

    public String getURI() {
        return uri;
    }

    public JsonNode getDefinitions() {
        return definitions;
    }

    public void setDefinitions(JsonNode definitions) {
        this.definitions = definitions;
    }
}

package org.kie.kogito.codegen.data;

import com.fasterxml.jackson.databind.JsonNode;

public class JacksonData {

    private JsonNode node;

    public JsonNode getNode() {
        return node;
    }

    public void setNode(JsonNode node) {
        this.node = node;
    }
}

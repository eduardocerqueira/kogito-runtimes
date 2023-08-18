package org.kie.kogito.jackson.utils;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class JsonNodeFactoryListener extends JsonNodeFactory {

    private static final long serialVersionUID = 1L;

    @Override
    public ObjectNode objectNode() {
        return new ObjectNodeListenerAware(this);
    }

    @Override
    public ArrayNode arrayNode() {
        return new ArrayNodeListenerAware(this);
    }

    @Override
    public ArrayNode arrayNode(int capacity) {
        return new ArrayNodeListenerAware(this, capacity);
    }

}

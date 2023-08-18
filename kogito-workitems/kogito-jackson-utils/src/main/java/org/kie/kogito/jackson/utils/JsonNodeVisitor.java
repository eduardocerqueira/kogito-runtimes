package org.kie.kogito.jackson.utils;

import java.util.Iterator;
import java.util.Map.Entry;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class JsonNodeVisitor {

    public static JsonNode transformTextNode(JsonNode node, UnaryOperator<JsonNode> function) {
        return transformNode(node, function, JsonNode::isTextual);
    }

    public static JsonNode transformNode(JsonNode node, UnaryOperator<JsonNode> function, Predicate<JsonNode> p) {
        if (node.isObject()) {
            final ObjectNode processedDefinition = ObjectMapperFactory.get().createObjectNode();
            final Iterator<Entry<String, JsonNode>> fields = node.fields();
            while (fields.hasNext()) {
                final Entry<String, JsonNode> jsonField = fields.next();
                processedDefinition.set(jsonField.getKey(), transformNode(jsonField.getValue(), function, p));
            }
            return processedDefinition;
        } else if (node.isArray()) {
            final ArrayNode processedDefinition = ObjectMapperFactory.get().createArrayNode();
            ((ArrayNode) node).forEach(item -> processedDefinition.add(transformNode(item, function, p)));
            return processedDefinition;
        } else if (p.test(node)) {
            return function.apply(node);
        }
        return node;
    }

    private JsonNodeVisitor() {
    }
}

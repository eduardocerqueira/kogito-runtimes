package org.kie.kogito.serverless.workflow.rpc;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.protobuf.Descriptors.Descriptor;
import com.google.protobuf.Descriptors.FieldDescriptor;
import com.google.protobuf.Descriptors.FieldDescriptor.Type;

public class DefaultEnumRpcDecorator implements RPCDecorator {

    @Override
    public JsonNode decorate(JsonNode node, Descriptor descriptor) {
        for (FieldDescriptor field : descriptor.getFields()) {
            if (node.has(field.getName())) {
                if (field.isRepeated()) {
                    node.get(field.getName()).forEach(n -> decorate(n, field.getMessageType()));
                } else if (field.getType() == Type.MESSAGE) {
                    decorate(node.get(field.getName()), field.getMessageType());
                }
            } else if (field.getType() == Type.ENUM) {
                ((ObjectNode) node).put(field.getName(), field.getDefaultValue().toString());
            }
        }
        return node;
    }
}

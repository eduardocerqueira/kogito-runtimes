package org.kie.kogito.serverless.workflow.rpc;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.protobuf.Descriptors.Descriptor;

public interface RPCDecorator {
    JsonNode decorate(JsonNode node, Descriptor typeDescriptor);
}

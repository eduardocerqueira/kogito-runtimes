package org.kie.kogito.serverless.workflow.rpc;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.protobuf.Message;

public interface RPCConverter {

    Message.Builder buildMessage(Object object, Message.Builder builder);

    JsonNode getJsonNode(Message message);
}

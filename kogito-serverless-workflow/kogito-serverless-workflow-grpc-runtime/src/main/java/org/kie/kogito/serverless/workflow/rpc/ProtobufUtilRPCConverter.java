package org.kie.kogito.serverless.workflow.rpc;

import java.io.IOException;
import java.io.UncheckedIOException;

import org.kie.kogito.jackson.utils.ObjectMapperFactory;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.protobuf.Message;
import com.google.protobuf.Message.Builder;
import com.google.protobuf.util.JsonFormat;

class ProtobufUtilRPCConverter implements RPCConverter {
    @Override
    public Builder buildMessage(Object object, Builder builder) {
        try {
            JsonFormat.parser().merge(ObjectMapperFactory.get().writeValueAsString(object), builder);
            return builder;
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    @Override
    public JsonNode getJsonNode(Message message) {
        StringBuilder sb = new StringBuilder();
        try {
            JsonFormat.printer().appendTo(message, sb);
            return ObjectMapperFactory.listenerAware().readTree(sb.toString());
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
